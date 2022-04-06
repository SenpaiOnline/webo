package online.senpai.webo.mapper

import online.senpai.webo.dto.blog.BlogPostDto
import online.senpai.webo.dto.blog.BlogPostPreviewDto
import online.senpai.webo.entity.blog.BlogPostEntity
import org.mapstruct.Mapper

@Mapper
interface BlogMapper {
    fun entityToPreviewDto(entity: BlogPostEntity): BlogPostPreviewDto
    fun entityToDto(entity: BlogPostEntity): BlogPostDto
}
