package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GithubUserId(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GithubUserId>(GithubUserIdsTable)

    var userId: User by User referencedOn GithubUserIdsTable.userId
}
