package online.senpai.webo.service

import arrow.core.Either
import arrow.core.NonEmptyList
import online.senpai.codegen.model.*
import online.senpai.webo.model.evolve.RowsNumberModel
import online.senpai.webo.repository.EvolveDataProviderError
import online.senpai.webo.repository.EvolveDomain
import online.senpai.webo.repository.EvolveRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class EvolveServiceImpl : EvolveService, KoinComponent {
    private val repository: EvolveRepository by inject()

    override suspend fun characterLines(
        characterName: EvolveAvailableCharacters
    ): Either<EvolveDataProviderError, EvolveVoiceLines> = repository
            .listAll(characterName.value)
            .map { listOfEvolveDomains: NonEmptyList<EvolveDomain> ->
                EvolveVoiceLines(
                    lines = listOfEvolveDomains
                        .map { domainModel: EvolveDomain ->
                            EvolveVoiceLine(
                                id = domainModel.lineName,
                                text = domainModel.lineText,
                                files = domainModel.filePath.toList()
                            )
                        }
                )
            }

    override suspend fun characterLines(
        characterName: EvolveAvailableCharacters,
        offset: Int,
        limit: Int,
        sortCriteria: EvolveVoiceLinesSortCriteria,
        sortOrder: SortOrder
    ): Either<EvolveDataProviderError, EvolveVoiceLines> = repository
            .listAll(characterName.value, offset, limit, sortCriteria, sortOrder)
            .map { listOfEvolveDomains: NonEmptyList<EvolveDomain> ->
                EvolveVoiceLines(
                    lines = listOfEvolveDomains
                        .map { domainModel: EvolveDomain ->
                            EvolveVoiceLine(
                                id = domainModel.lineName,
                                text = domainModel.lineText,
                                files = domainModel.filePath.toList()
                            )
                        }
                )
            }

    override suspend fun characterRowsNumber(
        name: String
    ): Either<EvolveDataProviderError, RowsNumberModel> = repository
            .rowsNumber(name)
            .map { RowsNumberModel(it) }
}
