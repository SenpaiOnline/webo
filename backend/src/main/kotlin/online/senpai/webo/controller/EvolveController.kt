package online.senpai.webo.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import online.senpai.webo.dto.evolve.EvolveDialogueDto
import online.senpai.webo.dto.evolve.EvolveDialoguesMetaDto
import online.senpai.webo.misc.EvolveCharacter
import online.senpai.webo.service.EvolveService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Validated
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/evolve")
class EvolveController @Inject constructor(
    private val service: EvolveService,
) {
    @Get("/meta/dialogues/{character}")
    fun countDialoguesByCharacter(
        @PathVariable character: EvolveCharacter
    ): Mono<EvolveDialoguesMetaDto> =
        service.characterMeta(character)

    @Get("/dialogues/{character}")
    fun findTwentyDialoguesByCharacter(
        @PathVariable character: EvolveCharacter,
        @QueryValue(defaultValue = "0") offset: Long
    ): Flux<EvolveDialogueDto> =
        service.findTwentyDialoguesByCharacter(character, offset)
}
