package online.senpai.webo.service

import online.senpai.webo.dto.blog.BlogPostDto
import online.senpai.webo.dto.blog.BlogPostPreviewDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BlogService {
    fun findPublishedPostsIds(): Flux<BlogPostPreviewDto>
    fun findPostById(id: Long): Mono<BlogPostDto>
}
