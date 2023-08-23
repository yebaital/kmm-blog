package code.yousef.models

import code.yousef.blog.shared.common.main.models.IPost
import code.yousef.blog.shared.common.main.models.enums.*
import code.yousef.models.dto.PostDTO
import code.yousef.utilities.InstantSerializer
import code.yousef.utilities.capitalizeWords
import code.yousef.utilities.slugify
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import kotlinx.serialization.Serializable
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
@PersistenceUnit
@Serializable
data class Post(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    override var id: String? = null,

    override var header: String,

    @Column(unique = true)
    override var postTitle: String,

    override var postSlug: String? = postTitle?.slugify(),

    @Column(columnDefinition = "text")
    override var summary: String,

    @Column(columnDefinition = "text")
    override var body: String,

    @ManyToOne
    var author: AppUser? = null,

    @CreationTimestamp
    @Serializable(with = InstantSerializer::class)
    var createdOn: Instant? = null,

    @UpdateTimestamp
    @Serializable(with = InstantSerializer::class)
    var updatedOn: Instant? = null,

    override var status: PostStatus,
    override var type: PostType,

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Category::class, fetch = FetchType.EAGER)
    override var categories: List<Category>,

    override var visits: Int? = 0,
    override var hasBeenUpdated: Boolean? = false,
    override var writtenLanguage: WrittenLanguage,
    override var programmingLanguage: ProgrammingLanguage,
) : IPost, PanacheEntityBase {
    fun toDTO(): PostDTO = PostDTO(
        header = this.header,
        postTitle = this.postTitle,
        summary = this.summary,
        body = this.body,
        status = this.status,
        type = this.type,
        categories = this.categories,
        writtenLanguage = this.writtenLanguage,
        programmingLanguage = this.programmingLanguage
    )
    companion object {
        fun fromDTO(dto: PostDTO): Post = Post(
            header = dto.header,
            postTitle = dto.postTitle.capitalizeWords(),
            body = dto.body,
            summary = dto.summary,
            status = dto.status,
            type = dto.type,
            categories = dto.categories,
            writtenLanguage = dto.writtenLanguage,
            programmingLanguage = dto.programmingLanguage
        )
    }
}