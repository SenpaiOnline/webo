package online.senpai.webo.dto.blog

import io.micronaut.core.annotation.Introspected

@Introspected
data class BlogPostDto(
    val id: Long,
    val title: String,
    val createdAt: String,
    val updatedAt: String,
    val content: String
)
