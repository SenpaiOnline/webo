package online.senpai.webo.repository

import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.reactive.ReactorCrudRepository
import online.senpai.webo.entity.blog.BlogPostEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface BlogRepository : ReactorCrudRepository<BlogPostEntity, Long> {
    fun findByPublishedTrueOrderById(): Flux<BlogPostEntity>
    fun findByIdAndPublishedTrue(id: Long): Mono<BlogPostEntity>
}
