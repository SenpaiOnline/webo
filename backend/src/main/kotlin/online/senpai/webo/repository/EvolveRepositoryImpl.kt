package online.senpai.webo.repository

import arrow.core.Either
import arrow.core.NonEmptyList
import online.senpai.codegen.model.EvolveVoiceLinesSortCriteria
import online.senpai.codegen.model.SortOrder
import online.senpai.webo.repository.ext.toSafeNonEmptyList
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.litote.kmongo.ascending
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.descending
import kotlin.reflect.KProperty1

private const val DATABASE_NAME = "evolve"

class EvolveRepositoryImpl : EvolveRepository, KoinComponent {
    private val client: CoroutineClient by inject()
    private val database: CoroutineDatabase = client.getDatabase(DATABASE_NAME)

    override suspend fun create(entity: EvolveDomain): Either<EvolveDataProviderError, EvolveDomain> {
        TODO("not implemented")
    }

    override suspend fun update(entity: EvolveDomain): Either<EvolveDataProviderError, EvolveDomain> {
        TODO("not implemented")
    }

    override suspend fun delete(entity: EvolveDomain): Either<EvolveDataProviderError, EvolveDomain> {
        TODO("not implemented")
    }

    override suspend fun listAll(): Either<EvolveDataProviderError, NonEmptyList<EvolveDomain>> {
        TODO("not implemented")
        /*return database.listCollectionNames().flatMap { name: String ->
            listAll(name)
        }*/
    }

    override suspend fun listAll(name: String): Either<EvolveDataProviderError, NonEmptyList<EvolveDomain>> =
        database
            .getCollectionIfExists<EvolveDomain>(name)
            .fold(
                ifLeft = ::couldNotConnectToDatabase,
                ifRight = { collection: CoroutineCollection<EvolveDomain>? ->
                    collection
                        ?.find()
                        ?.sort(ascending(EvolveDomain::lineName))
                        ?.toSafeNonEmptyList()
                        ?.toEither { EvolveDataProviderError.CharacterHasNoData }
                        ?: Either.left(EvolveDataProviderError.CharacterDoesNotExist)
                }
            )

    override suspend fun listAll(
        name: String,
        offset: Int,
        limit: Int,
        sortCriteria: EvolveVoiceLinesSortCriteria,
        sortOrder: SortOrder
    ): Either<EvolveDataProviderError, NonEmptyList<EvolveDomain>> =
        database
            .getCollectionIfExists<EvolveDomain>(name)
            .fold(
                ifLeft = ::couldNotConnectToDatabase,
                ifRight = { collection: CoroutineCollection<EvolveDomain>? ->
                    collection
                        ?.find()
                        ?.sort(
                            when (sortOrder) {
                                SortOrder.ASC -> ascending(sortCriteria.selectProperty())
                                SortOrder.DESC -> descending(sortCriteria.selectProperty())
                            }
                        )
                        ?.skip(offset)
                        ?.limit(limit)
                        ?.toSafeNonEmptyList()
                        ?.toEither { EvolveDataProviderError.CharacterHasNoData }
                        ?: Either.left(EvolveDataProviderError.CharacterDoesNotExist)
                }
            )

    override suspend fun rowsNumber(name: String): Either<EvolveDataProviderError, Long> =
        database
            .getCollectionIfExists<EvolveDomain>(name)
            .fold(
                ifLeft = ::couldNotConnectToDatabase,
                ifRight = { collection: CoroutineCollection<EvolveDomain>? ->
                    if (collection == null) {
                        Either.left(EvolveDataProviderError.CharacterDoesNotExist)
                    } else {
                        val numberOfDocuments: Long = collection.countDocuments()
                        Either.conditionally(
                            test = numberOfDocuments > 0,
                            ifTrue = { numberOfDocuments },
                            ifFalse = { EvolveDataProviderError.CharacterHasNoData }
                        )
                    }
                }
            )
}

private fun EvolveVoiceLinesSortCriteria.selectProperty(): KProperty1<EvolveDomain, String> = when (this) {
    EvolveVoiceLinesSortCriteria.ID -> EvolveDomain::lineName
    EvolveVoiceLinesSortCriteria.TEXT -> EvolveDomain::lineText
}

private fun couldNotConnectToDatabase(
    throwable: Throwable
): Either<EvolveDataProviderError.CouldNotConnectToDatabase, Nothing> =
    Either.left(EvolveDataProviderError.CouldNotConnectToDatabase(throwable))
