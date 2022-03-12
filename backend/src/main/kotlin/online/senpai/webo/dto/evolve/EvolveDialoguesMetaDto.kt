package online.senpai.webo.dto.evolve

import io.micronaut.core.annotation.Introspected

@Introspected
data class EvolveDialoguesMetaDto(
    val rowsNumber: Long,
    val rowsPerPage: Long
)
