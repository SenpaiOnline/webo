package online.senpai.webo.handler.validator

import arrow.core.Validated
import com.capraro.kalidation.constraints.function.positiveOrZero
import com.capraro.kalidation.constraints.function.range
import com.capraro.kalidation.dsl.constraints
import com.capraro.kalidation.dsl.property
import com.capraro.kalidation.dsl.validationSpec
import com.capraro.kalidation.spec.ValidationResult
import io.ktor.locations.*
import online.senpai.codegen.Paths.EvolveVoiceLinesFindByName

@KtorExperimentalLocationsAPI
object EvolveValidator {
    val voiceLinesFindByNameSpec = validationSpec {
        constraints<EvolveVoiceLinesFindByName> {
            property(EvolveVoiceLinesFindByName::limit) {
                range(1, 25)
            }
            property(EvolveVoiceLinesFindByName::offset) {
                positiveOrZero()
            }
        }
    }
}

@KtorExperimentalLocationsAPI
fun EvolveVoiceLinesFindByName.validate(): Validated<Set<ValidationResult>, Boolean> =
    EvolveValidator.voiceLinesFindByNameSpec.validate(this)
