package online.senpai.webo.dto.blog

import io.micronaut.core.annotation.Introspected
import io.swagger.v3.oas.annotations.media.Schema

@Introspected
data class BlogPostDto(
    val id: Long,
    val title: String,
    @Schema(format = "date-time") val createdAt: String,
    @Schema(format = "date-time") val updatedAt: String,
    val content: String
)
