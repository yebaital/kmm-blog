package code.yousef.blog.shared.common.main.models.dto

import code.yousef.blog.shared.common.main.models.enums.*

interface IPostDTO {
    var header: String
    var postTitle: String
    var summary: String
    var body: String
    var status: PostStatus
    var type: PostType
    var categories: List<Category>
    var writtenLanguage: WrittenLanguage
    var programmingLanguage: ProgrammingLanguage
}