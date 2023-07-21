package code.yousef.blog.models

interface ISubcategory {
    var id: Long?
    var subcategoryTitle: String
    var subcategorySlug: String
    var category: ICategory // Many to one
    var posts: List<IPost>? // Many to many
}
