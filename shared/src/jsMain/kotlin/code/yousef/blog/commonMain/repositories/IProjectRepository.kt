package code.yousef.blog.repositories

import code.yousef.blog.models.IProject

interface IProjectRepository {
    fun findByProjectSlug(slug: String): IProject
}