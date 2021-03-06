package online.senpai.webo.repository

import online.senpai.webo.domain.EvolveCharacterLine

typealias EvolveDomain = EvolveCharacterLine

interface EvolveRepository : MongoCrudRepository<EvolveDomain> {
    suspend fun listAll(name: String): Iterable<EvolveDomain>
    suspend fun listAll(
        name: String,
        startRow: Int,
        count: Int,
        sortBy: String,
        descending: Boolean = false
    ): Iterable<EvolveDomain>
    suspend fun rowsNumber(name: String): Long
}
