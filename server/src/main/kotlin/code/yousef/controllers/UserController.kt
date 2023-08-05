package code.yousef.controllers

import code.yousef.models.AppUser
import code.yousef.models.dto.AuthResponseDTO
import code.yousef.models.dto.SignInDTO
import code.yousef.services.AppUserService
import code.yousef.utilities.TokenUtils
import io.quarkus.elytron.security.common.BcryptUtil
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.annotation.security.PermitAll
import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.inject.ConfigProperty


@Path("/users")
@RequestScoped
class UserController(private val userService: AppUserService) {

    @ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration")
    var duration: Long? = null

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    var issuer: String? = null

    @PermitAll
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    suspend fun login(signInDTO: SignInDTO): Response {
        val user: AppUser? = userService.findByUsername(signInDTO.username).awaitSuspending()

        return if (user != null && BcryptUtil.matches(signInDTO.password, user.password)) {
            try {
                Response.ok(AuthResponseDTO(TokenUtils.generateToken(user.username, user.roles, duration!!, issuer!!)))
                    .build()
            } catch (e: Exception) {
                Response.status(Response.Status.UNAUTHORIZED).build()
            }
        } else {
            Response.status(Response.Status.UNAUTHORIZED).build()
        }
    }

    @GET
    @Path("/{username}")
    suspend fun findByUsername(@PathParam("username") username: String): AppUser? {
        return userService.findByUsername(username).awaitSuspending()
    }

}