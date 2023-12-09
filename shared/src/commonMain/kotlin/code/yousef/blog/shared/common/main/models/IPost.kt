package code.yousef.blog.shared.common.main.models

import code.yousef.blog.shared.common.main.models.enums.*


interface IPost {
    var id: String?
    var header: String?
    var postTitle: String
    var postSlug: String?
    var summary: String
    var body: String
    var status: PostStatus
    var type: PostType
    var categories: List<Category> // Many to one
    var visits: Int?
    var hasBeenUpdated: Boolean?
    var writtenLanguage: WrittenLanguage
    var programmingLanguage: ProgrammingLanguage
}