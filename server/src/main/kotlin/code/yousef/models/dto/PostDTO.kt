package code.yousef.models.dto

import code.yousef.blog.shared.common.main.models.dto.IPostDTO
import code.yousef.blog.shared.common.main.models.enums.*
import kotlinx.serialization.Serializable

@Serializable
data class PostDTO(
    override var header: String,
    override var postTitle: String,
    override var summary: String,
    override var body: String,
    override var status: PostStatus,
    override var type: PostType,
    override var categories: List<Category>,
    override var writtenLanguage: WrittenLanguage,
    override var programmingLanguage: ProgrammingLanguage
):IPostDTO
