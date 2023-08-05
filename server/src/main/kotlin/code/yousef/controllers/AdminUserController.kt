package code.yousef.controllers

import code.yousef.models.AppUser
import code.yousef.services.AppUserService
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@ApplicationScoped
@Path("/admin/users")
class AdminUserController(private val appUserService: AppUserService) {

    @POST
    suspend fun createUser(user:AppUser): Uni<AppUser> {
        return  appUserService.saveUser(user)
    }


}