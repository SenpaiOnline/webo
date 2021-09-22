@file:Suppress("RemoveExplicitTypeArguments")

package online.senpai.webo.handler

import com.capraro.kalidation.spec.ValidationResult
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import mu.KLogger
import mu.KotlinLogging
import online.senpai.codegen.Paths
import online.senpai.codegen.model.ResponseError
import online.senpai.webo.dto.UnsuccessfulJsonResponse
import online.senpai.webo.dto.evolve.RowsNumberDto
import online.senpai.webo.handler.validator.validate
import online.senpai.webo.repository.EvolveDataProviderError
import online.senpai.webo.service.EvolveService
import org.koin.ktor.ext.inject

private val logger: KLogger = KotlinLogging.logger {}

@KtorExperimentalLocationsAPI
@Location("/rowsNumberOf/{name}")
data class RowsNumberOf(val name: String)

@KtorExperimentalLocationsAPI
fun Route.evolveHandler() {
    val evolveService: EvolveService by inject()

    get<RowsNumberOf> { requestParams: RowsNumberOf ->
        evolveService
            .characterRowsNumber(requestParams.name)
            .fold(
                ifLeft = { call.respond(UnsuccessfulJsonResponse(message = it.errorMessage)) },
                ifRight = { call.respond(RowsNumberDto.fromEntity(it)) }
            )
    }

    get<Paths.EvolveVoiceLinesFindByName> { requestParams: Paths.EvolveVoiceLinesFindByName ->
        requestParams
            .validate()
            .fold(
                fe = { set: Set<ValidationResult> ->
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = ResponseError(
                            code = "400",
                            message = set.joinToString(separator = "; ") { result: ValidationResult ->
                                "${result.fieldName} ${result.message}, currently the value is ${result.invalidValue}"
                            }
                        )
                    )
                },
                fa = { _: Boolean ->
                    evolveService
                        .characterLines(
                            characterName = requestParams.name,
                            offset = requestParams.offset,
                            limit = requestParams.limit,
                            sortCriteria = requestParams.sortCriteria,
                            sortOrder = requestParams.sortOrder
                        )
                        .fold(
                            ifLeft = { providerError: EvolveDataProviderError ->
                                logger.error { providerError.show }
                                /*logger.error { "${providerError.errorMessage}, request params: $requestParams" }*/
                                /*logger.error { "${providerError.errorMessage}, request params: $requestParams" }*/
                                /*logger.error(providerError.throwable) {
                                                                   "${providerError.errorMessage}, request params: $requestParams"
                                                               }*/
                                when (providerError) {
                                    is EvolveDataProviderError.CharacterDoesNotExist -> call.respond(
                                        status = HttpStatusCode.NotFound,
                                        message = ResponseError(code = "404", message = providerError.errorMessage)
                                    )
                                    is EvolveDataProviderError.CharacterHasNoData -> call.respond(
                                        status = HttpStatusCode.InternalServerError,
                                        message = ResponseError(code = "500", message = providerError.errorMessage)
                                    )
                                    is EvolveDataProviderError.CouldNotConnectToDatabase -> call.respond(
                                        status = HttpStatusCode.InternalServerError,
                                        message = ResponseError(code = "500", message = providerError.errorMessage)
                                    )
                                }
                            },
                            ifRight = { call.respond(it) }
                        )
                }
            )
    }
}
