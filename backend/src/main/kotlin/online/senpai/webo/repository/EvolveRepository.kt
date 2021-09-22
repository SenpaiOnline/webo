package online.senpai.webo.repository

import arrow.core.Either
import arrow.core.NonEmptyList
import online.senpai.codegen.model.EvolveVoiceLinesSortCriteria
import online.senpai.codegen.model.SortOrder
import online.senpai.webo.domain.EvolveCharacterLine

typealias EvolveDomain = EvolveCharacterLine

interface EvolveRepository : SuspendableMongoCrudRepository<EvolveDomain, EvolveDataProviderError> {
    suspend fun listAll(name: String): Either<EvolveDataProviderError, NonEmptyList<EvolveDomain>>
    suspend fun listAll(
        name: String,
        offset: Int,
        limit: Int,
        sortCriteria: EvolveVoiceLinesSortCriteria,
        sortOrder: SortOrder
    ): Either<EvolveDataProviderError, NonEmptyList<EvolveDomain>>
    suspend fun rowsNumber(name: String): Either<EvolveDataProviderError, Long>
}
