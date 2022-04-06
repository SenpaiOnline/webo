package online.senpai.webo.entity.blog

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "blog_post")
class BlogPostEntity(
    @Id
    @GeneratedValue
    val id: Long,

    @Column(unique = true, length = 100)
    val title: String,

    val published: Boolean,

    @Column(name = "created_at")
    @DateCreated
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    @DateUpdated
    val updatedAt: LocalDateTime,

    /*@Column(name = "published_at")
    val publishedAt: LocalDateTime,*/

    @Column(columnDefinition = "text")
    val content: String
)
