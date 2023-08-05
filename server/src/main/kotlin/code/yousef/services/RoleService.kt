package code.yousef.services

import code.yousef.blog.shared.common.main.models.enums.RoleName
import code.yousef.models.Role
import code.yousef.repositories.RoleRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class RoleService(val roleRepository: RoleRepository) {

    fun findByName(name: RoleName) = roleRepository.findByName(name)
    fun saveRole(role: Role) = roleRepository.saveRole(role)

}