package code.yousef.blog.repositories

import code.yousef.blog.models.IAppUser

interface IAppUserRepository {
    fun findByUsername(username: String): IAppUser?
}