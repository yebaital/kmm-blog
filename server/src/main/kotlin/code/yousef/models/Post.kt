package code.yousef.models

import code.yousef.blog.shared.common.main.models.IPost
import code.yousef.blog.shared.common.main.models.enums.*
import code.yousef.utilities.InstantSerializer
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

    override var header: String? = null,

    @Column(unique = true)
    override var postTitle: String? = null,

    override var postSlug: String? = postTitle?.slugify(),

    @Column(columnDefinition = "text")
    override var summary: String? = null,

    @Column(columnDefinition = "text")
    override var body: String? = null,

    @ManyToOne
    var author: AppUser,

    @CreationTimestamp
    @Serializable(with = InstantSerializer::class)
    var createdOn: Instant? = null,

    @UpdateTimestamp
    @Serializable(with = InstantSerializer::class)
    var updatedOn: Instant? = null,

    override var status: PostStatus? = null,
    override var type: PostType? = null,

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Category::class, fetch = FetchType.EAGER)
    override var categories: List<Category>? = null,

    override var visits: Int? = 0,
    override var hasBeenUpdated: Boolean? = false,
    override var writtenLanguage: WrittenLanguage? = null,
    override var programmingLanguage: ProgrammingLanguage? = null
) : IPost, PanacheEntityBase