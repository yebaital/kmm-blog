package code.yousef.blog.repositories

import code.yousef.blog.models.ICategory

interface ICategoryRepository {
    fun findByCategoryTitle(title: String): ICategory?
    fun findByOrderByCategoryTitleAsc(): List<ICategory>
    fun findByCategorySlug(categorySlug: String): ICategory?
}