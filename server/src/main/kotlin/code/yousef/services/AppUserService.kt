package code.yousef.services

import code.yousef.models.AppUser
import code.yousef.models.Role
import code.yousef.repositories.AppUserRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AppUserService(val appUserRepository: AppUserRepository) {

    fun saveUser(user: AppUser) = appUserRepository.saveUser(user)


    fun findByUsername(username: String) = appUserRepository.findByUsername(username)

    suspend fun findByEmail(email: String) = appUserRepository.findByEmail(email)

    suspend fun findByRole(role: Role, page: Int) = appUserRepository.findByRole(role, page)

    suspend fun enableUser(username: String) = appUserRepository.enableUser(username)

    suspend fun deleteUser(username: String) = appUserRepository.deleteUser(username)

}