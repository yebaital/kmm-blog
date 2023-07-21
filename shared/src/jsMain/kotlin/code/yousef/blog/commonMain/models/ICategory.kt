package code.yousef.blog.models

interface ICategory {
    var id: Long?
    var categoryTitle: String
    var categorySlug: String
    var subCategories: List<ISubcategory>? // one to many
    var posts: List<IPost>? // one to many
}