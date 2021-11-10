package online.senpai.webo.dto.evolve

import io.micronaut.core.annotation.Introspected

@Introspected
data class EvolveDialogueDto(
    val name: String,
    val text: String,
    val audio: List<String>?
)
