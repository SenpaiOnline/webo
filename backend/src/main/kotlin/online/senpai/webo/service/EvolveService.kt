package online.senpai.webo.service

import online.senpai.webo.EvolveCharacter
import online.senpai.webo.dto.evolve.EvolveDialogueDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface EvolveService {
    fun countByCharacter(character: EvolveCharacter): Mono<Number>
    fun findTwentyDialoguesByCharacter(character: EvolveCharacter, offset: Long): Flux<EvolveDialogueDto>
}
