package online.senpai.webo.repository

import arrow.core.Either
import arrow.core.NonEmptyList
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

interface SuspendableMongoCrudRepository<T, L> {
    suspend fun create(entity: T): Either<L, T>
    suspend fun update(entity: T): Either<L, T>
    suspend fun delete(entity: T): Either<L, T>
    suspend fun listAll(): Either<L, NonEmptyList<T>>
}

/**
 * The function checks if a collection with the given name exists in the database.
 * It relies on MongoDB's internal way of filtering collections and should only take less than 1ms to search through 10k
 * collections instead of iteration on the driver side.
 */
suspend fun CoroutineDatabase.checkIfCollectionExists(name: String): Either<Throwable, Boolean> =
    Either.catch {
        this // we've got a hole in security, but thanks god, at least we've got something in security! :p
            .runCommand<ListCollectionResult>("{'listCollections': 1, nameOnly: true, filter: {name: '$name'}}")
            ?.cursor // TODO rewrite me
            ?.firstBatch
            ?.firstOrNull()
            ?.name == name
    }

/**
 * The function tries to get a collection with the given name.
 * @param T the type of a class to map a collection
 * @param name the name of the collection
 * @return either [CoroutineCollection<T>?] if a collection with the given name exists or [Throwable]
 */
suspend inline fun <reified T : Any> CoroutineDatabase.getCollectionIfExists(
    name: String
): Either<Throwable, CoroutineCollection<T>?> =
    checkIfCollectionExists(name)
        .map { exists: Boolean ->
            if (exists) {
                Either
                    .catch { this.getCollection<T>(name) }
                    .fold(
                        ifLeft = { null },
                        ifRight = { it }
                    )
            } else {
                null
            }
        }

    /*return retryOrElseEither(
            schedule = Schedule
                .spaced<CoroutineCollection<T>>(delayBetweenTries.milliseconds) and recurs(amountOfTries),
            fa = { this.getCollection(name) },
            orElse = { throwable: Throwable, _: Pair<Int, Int> -> throwable }
    )).map()
    if (!checkIfCollectionExists(name)) {
        return Either.left(IllegalArgumentException("Couldn't find a collection with the given name!"))
    }

    return retryOrElseEither(
        schedule = Schedule.spaced<CoroutineCollection<T>>(delay.milliseconds) and recurs(amountOfTries),
        fa = { this.getCollection(name) },
        orElse = { throwable: Throwable, _: Pair<Int, Int> -> throwable }
    )
}*/

data class ListCollectionResult( // TODO move it somewhere?
    val cursor: Cursor,
    val ok: Int
) {
    data class Cursor(
        val firstBatch: List<FirstBatch?>,
        val id: Int,
        val ns: String
    ) {
        data class FirstBatch(
            val name: String,
            val type: String
        )
    }
}
