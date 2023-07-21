package code.yousef.blog.repositories

import code.yousef.blog.models.ICategory
import code.yousef.blog.models.ISubcategory

interface ISubcategoryRepository {
    fun findByCategory(category: ICategory): List<ISubcategory>?
    fun findBySubcategorySlug(categorySlug: String): ISubcategory?
}
