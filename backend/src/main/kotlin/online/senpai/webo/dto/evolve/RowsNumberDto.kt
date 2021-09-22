package online.senpai.webo.dto.evolve

import online.senpai.webo.dto.GenericJsonResponse
import online.senpai.webo.model.evolve.RowsNumberModel

data class RowsNumberDto(
    val rowsNumber: Number,
    override val message: String = ""
) : GenericJsonResponse {
    override val success: Boolean = true

    companion object {
        fun fromEntity(entity: RowsNumberModel): RowsNumberDto {
            return RowsNumberDto(entity.rowsNumber)
        }
    }
}
