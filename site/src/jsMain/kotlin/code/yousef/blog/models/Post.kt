package code.yousef.blog.models

import code.yousef.blog.shared.common.main.models.IPost
import code.yousef.blog.shared.common.main.models.enums.*

data class Post(
    override var id: String?,
    override var header: String?,
    override var postTitle: String?,
    override var postSlug: String?,
    override var summary: String?,
    override var body: String?,
    override var status: PostStatus?,
    override var type: PostType?,
    override var visits: Int?,
    override var hasBeenUpdated: Boolean?,
    override var writtenLanguage: WrittenLanguage?,
    override var programmingLanguage: ProgrammingLanguage?,
    override var categories: List<Category>?
) : IPost
