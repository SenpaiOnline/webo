package online.senpai.webo.service

import online.senpai.webo.dto.evolve.EvolveDialogueDto
import online.senpai.webo.dto.evolve.EvolveDialoguesMetaDto
import online.senpai.webo.misc.EvolveCharacter
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private const val ROWS_PER_PAGE: Long = 20

interface EvolveService {
    fun characterMeta(character: EvolveCharacter, rowsPerPage: Long = ROWS_PER_PAGE): Mono<EvolveDialoguesMetaDto>
    fun findTwentyDialoguesByCharacter(character: EvolveCharacter, offset: Long): Flux<EvolveDialogueDto>
}
