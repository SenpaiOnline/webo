package online.senpai.webo.service

import online.senpai.webo.dto.evolve.EvolveDialogueDto
import online.senpai.webo.dto.evolve.EvolveDialoguesMetaDto
import online.senpai.webo.misc.EvolveCharacter
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface EvolveService {
    fun characterMeta(character: EvolveCharacter): Mono<EvolveDialoguesMetaDto>
    fun findTwentyDialoguesByCharacter(character: EvolveCharacter, offset: Long): Flux<EvolveDialogueDto>
}
