package online.senpai.webo.service

import jakarta.inject.Singleton
import online.senpai.webo.dto.blog.BlogPostDto
import online.senpai.webo.dto.blog.BlogPostPreviewDto
import online.senpai.webo.mapper.BlogMapper
import online.senpai.webo.repository.BlogRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Singleton
class BlogServiceImpl(private val repository: BlogRepository, private val mapper: BlogMapper) : BlogService {
    override fun findPublishedPostsIds(): Flux<BlogPostPreviewDto> =
        repository.findByPublishedTrueOrderById().map(mapper::entityToPreviewDto)

    override fun findPostById(id: Long): Mono<BlogPostDto> =
        repository.findByIdAndPublishedTrue(id).map(mapper::entityToDto)
}
