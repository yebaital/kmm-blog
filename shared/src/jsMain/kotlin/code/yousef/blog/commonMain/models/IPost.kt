package code.yousef.blog.models

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime

interface IPost{
    var id: Uuid?
    var header: String
    var postTitle: String
    var postSlug: String?
    var summary: String
    var body: String
    var createdOn: LocalDateTime?
    var updatedOn: LocalDateTime?
    var status: PostStatus
    var type: PostType
    var category: ICategory // Many to one
    var subcategories: List<ISubcategory> // Many to many
    var author: IAppUser // Many to one
    var visits: Int?
    var hasBeenUpdated: Boolean?
    var writtenLanguage: WrittenLanguage?
    var programmingLanguage: ProgrammingLanguage?
}