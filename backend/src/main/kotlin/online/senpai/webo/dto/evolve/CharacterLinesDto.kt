package online.senpai.webo.dto.evolve

import online.senpai.webo.dto.GenericJsonResponse

data class CharacterLinesDto(
    val lines: Iterable<LineModel>,
    override val message: String = ""
) : GenericJsonResponse {
    override val success: Boolean = true

    data class LineModel(
        val name: String,
        val text: String,
        val files: Iterable<String>
    )

    /*companion object {
        fun fromEntity(entity: LinesModel): CharacterLinesDto {
            return CharacterLinesDto()
        }
    }*/
}
