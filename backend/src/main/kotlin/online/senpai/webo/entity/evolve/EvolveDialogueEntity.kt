package online.senpai.webo.entity.evolve

import io.micronaut.data.annotation.Index
import io.micronaut.data.annotation.MappedEntity
import online.senpai.webo.entity.common.GenericInGameDialogueEntity
import online.senpai.webo.misc.EvolveCharacter

@MappedEntity("evolve_dialogues")
@Index(columns = ["name"], unique = true)
data class EvolveDialogueEntity(
    override val id: Long,
    override val character: EvolveCharacter,
    override val name: String,
    override val text: String,
    override val audio: List<String>?
) : GenericInGameDialogueEntity<EvolveCharacter>
