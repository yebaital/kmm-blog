package code.yousef.blog.repositories

import code.yousef.blog.models.IRole
import code.yousef.blog.models.RoleName

interface IRoleRepository {
    fun findByName(roleName: RoleName): IRole?
}