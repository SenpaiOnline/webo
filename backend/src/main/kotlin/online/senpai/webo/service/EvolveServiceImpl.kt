package online.senpai.webo.service

import jakarta.inject.Inject
import jakarta.inject.Singleton
import online.senpai.webo.dto.evolve.EvolveDialogueDto
import online.senpai.webo.dto.evolve.EvolveDialoguesMetaDto
import online.senpai.webo.mapper.EvolveDialogueMapper
import online.senpai.webo.misc.EvolveCharacter
import online.senpai.webo.repository.EvolveDialoguesRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.context.Context
import reactor.util.context.ContextView

private const val ROWS_PER_PAGE: Long = 20

@Singleton
class EvolveServiceImpl @Inject constructor(
    private val repository: EvolveDialoguesRepository,
    private val mapper: EvolveDialogueMapper
) : EvolveService {
    override fun characterMeta(character: EvolveCharacter): Mono<EvolveDialoguesMetaDto> =
        Mono.just(character)
            .flatMap { char: EvolveCharacter ->
                Mono.deferContextual { ctx: ContextView ->
                    repository
                        .countByCharacter(char)
                        .map { rowsNumber: Number ->
                            EvolveDialoguesMetaDto(
                                rowsNumber = rowsNumber.toLong(),
                                rowsPerPage = ctx.get("rowsPerPage")
                            )
                        }
                }
            }
            .contextWrite { ctx: Context -> ctx.put("rowsPerPage", ROWS_PER_PAGE) }

    override fun findTwentyDialoguesByCharacter(character: EvolveCharacter, offset: Long): Flux<EvolveDialogueDto> =
        repository.findByCharacter(character, offset).map(mapper::entityToDto)
}
