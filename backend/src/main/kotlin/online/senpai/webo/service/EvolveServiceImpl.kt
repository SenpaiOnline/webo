package online.senpai.webo.service

import jakarta.inject.Inject
import jakarta.inject.Singleton
import online.senpai.webo.EvolveCharacter
import online.senpai.webo.EvolveDialoguesRepository
import online.senpai.webo.dto.evolve.EvolveDialogueDto
import online.senpai.webo.mapper.EvolveDialogueMapper
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class EvolveServiceImpl @Inject constructor(
    private val repository: EvolveDialoguesRepository,
    private val mapper: EvolveDialogueMapper
) : EvolveService {
    override fun countByCharacter(character: EvolveCharacter): Mono<Number> =
        repository.countByCharacter(character)

    override fun findTwentyDialoguesByCharacter(character: EvolveCharacter, offset: Long): Flux<EvolveDialogueDto> =
        repository.findByCharacter(character, offset).map(mapper::entityToDto)
}
