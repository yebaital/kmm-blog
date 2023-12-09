package code.yousef.blog.models

import code.yousef.blog.shared.common.main.models.IPost
import code.yousef.blog.shared.common.main.models.enums.*
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    override var id: String? = null,
    override var header: String? = null,
    override var postTitle: String,
    override var postSlug: String? = null,
    override var summary: String,
    override var body: String,
    override var status: PostStatus,
    override var type: PostType,
    override var visits: Int? = null,
    override var hasBeenUpdated: Boolean? = null,
    override var writtenLanguage: WrittenLanguage,
    override var programmingLanguage: ProgrammingLanguage,
    override var categories: List<Category>
) : IPost
