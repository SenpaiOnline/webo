package online.senpai.webo.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.validation.Validated
import online.senpai.webo.dto.blog.BlogPostDto
import online.senpai.webo.dto.blog.BlogPostPreviewDto
import online.senpai.webo.service.BlogService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Validated
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/blog")
class BlogController(private val blogService: BlogService) {
    @Get("/posts")
    fun findPublishedPostsIds(): Flux<BlogPostPreviewDto> = blogService.findPublishedPostsIds()

    @Get("/post/{id}")
    fun findPost(@PathVariable("id") id: Long): Mono<BlogPostDto> = blogService.findPostById(id)
}
