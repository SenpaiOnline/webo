package online.senpai.webo.dto.blog

import io.micronaut.core.annotation.Introspected

@Introspected
data class BlogPostPreviewDto(
    val id: Long,
    val title: String
)
