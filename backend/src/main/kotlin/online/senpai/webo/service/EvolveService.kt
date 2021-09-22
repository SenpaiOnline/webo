package online.senpai.webo.service

import arrow.core.Either
import online.senpai.codegen.model.EvolveAvailableCharacters
import online.senpai.codegen.model.EvolveVoiceLines
import online.senpai.codegen.model.EvolveVoiceLinesSortCriteria
import online.senpai.codegen.model.SortOrder
import online.senpai.webo.model.evolve.RowsNumberModel
import online.senpai.webo.repository.EvolveDataProviderError

interface EvolveService {
    suspend fun characterLines(
        characterName: EvolveAvailableCharacters
    ): Either<EvolveDataProviderError, EvolveVoiceLines>
    suspend fun characterLines(
        characterName: EvolveAvailableCharacters,
        offset: Int,
        limit: Int,
        sortCriteria: EvolveVoiceLinesSortCriteria,
        sortOrder: SortOrder
    ): Either<EvolveDataProviderError, EvolveVoiceLines>
    suspend fun characterRowsNumber(name: String): Either<EvolveDataProviderError, RowsNumberModel>
}
