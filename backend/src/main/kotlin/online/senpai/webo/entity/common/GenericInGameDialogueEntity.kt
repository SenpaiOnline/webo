package online.senpai.webo.entity.common

import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.model.DataType
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id

interface GenericInGameDialogueEntity<E : Enum<E>> {
    @get:Id
    @get:GeneratedValue
    val id: Long

    @get:Enumerated(EnumType.STRING)
    val character: E
    val name: String
    val text: String

    @get:TypeDef(type = DataType.STRING_ARRAY)
    val audio: List<String>?
}
