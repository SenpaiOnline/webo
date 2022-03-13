package online.senpai.webo.entity.evolve

import io.micronaut.data.annotation.MappedEntity
import online.senpai.webo.entity.common.GenericInGameDialogueEntity
import online.senpai.webo.misc.EvolveCharacter
import javax.persistence.Index
import javax.persistence.Table

@MappedEntity
@Table(name = "evolve_dialogues", indexes = [Index(columnList = "name", unique = true)])
class EvolveDialogueEntity(
    override val id: Long,
    override val character: EvolveCharacter,
    override val name: String,
    override val text: String,
    override val audio: List<String>?
) : GenericInGameDialogueEntity<EvolveCharacter>
