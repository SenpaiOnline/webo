package online.senpai.webo.mapper

import online.senpai.webo.dto.evolve.EvolveDialogueDto
import online.senpai.webo.entity.evolve.EvolveDialogueEntity
import org.mapstruct.Mapper

@Mapper
interface EvolveDialogueMapper {
    fun entityToDto(entity: EvolveDialogueEntity): EvolveDialogueDto
    fun dtoToEntity(dto: EvolveDialogueDto): EvolveDialogueEntity
}
