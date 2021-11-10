package online.senpai.webo

import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorCrudRepository
import online.senpai.webo.entity.evolve.EvolveDialogueEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface EvolveDialoguesRepository : ReactorCrudRepository<EvolveDialogueEntity, Long> {
    fun countByCharacter(character: EvolveCharacter): Mono<Number>

    @Query(
        """
        SELECT d.id, d.character, d.name, d.text, d.audio
        FROM evolve_dialogues d
        WHERE d.character = :character
        ORDER BY d.name
        OFFSET :offset
        FETCH NEXT 20 ROWS ONLY
        """,
        nativeQuery = true
    )
    fun findByCharacter(character: EvolveCharacter, offset: Long): Flux<EvolveDialogueEntity>
}
