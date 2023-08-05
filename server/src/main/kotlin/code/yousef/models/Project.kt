package code.yousef.models

import code.yousef.blog.shared.common.main.models.IProject
import code.yousef.utilities.slugify
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntityBase
import jakarta.persistence.*
import kotlinx.serialization.Serializable
import org.hibernate.annotations.GenericGenerator

@Entity
@Serializable
data class Project(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    override var id: String?,
    override var projectName: String,
    override var projectNameArabic: String,
    override var projectSlug: String? = projectName.slugify(),
    override var projectHeader: String,

    @Column(columnDefinition = "text")
    override var projectDescription: String,

    @Column(columnDefinition = "text")
    override var projectDescriptionArabic: String,

    @Column(columnDefinition = "text")
    override var projectSummary: String,

    @Column(columnDefinition = "text")
    override var projectSummaryArabic: String,

    @ElementCollection
    override var projectScreenShots: List<String>,


    override var github: String?,
    override var website: String?,
    override var playStore: String?,
    override var appStore: String?,
    override var twitter: String?,
    override var instagram: String?
) : IProject, PanacheEntityBase
