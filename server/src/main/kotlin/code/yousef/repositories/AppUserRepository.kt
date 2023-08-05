package code.yousef.repositories

import code.yousef.models.AppUser
import code.yousef.models.Role
import io.quarkus.elytron.security.common.BcryptUtil
import io.quarkus.hibernate.reactive.panache.common.WithSessionOnDemand
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import io.quarkus.panache.common.Page
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import java.time.Duration

@ApplicationScoped
@WithSessionOnDemand
class AppUserRepository : PanacheRepository<AppUser> {

    @WithTransaction
    fun saveUser(user: AppUser): Uni<AppUser> {
        user.password = BcryptUtil.bcryptHash(user.password)
        return persistAndFlush(user)
            .replaceWith(user)
            .ifNoItem().after(Duration.ofMillis(10000)).fail()
            .onFailure().transform { t -> IllegalStateException(t) }
            .onItem().transform { it }
    }

    fun findByUsername(username: String): Uni<AppUser?> = find("username", username).firstResult()
    suspend fun findByEmail(email: String): Uni<AppUser?> = find("email", email).firstResult()

    suspend fun findByRole(role: Role, page: Int): Uni<List<AppUser>> {
        val users: PanacheQuery<AppUser> = find("role = ?1", role)

        return users.page(Page.of(page, 10)).list()
    }

    suspend fun enableUser(username: String): Uni<AppUser?> {
        return findByUsername(username).onItem().ifNotNull().invoke { user ->
            user!!.isEnabled = true
            persistAndFlush(user)
        }

    }


    suspend fun deleteUser(username: String): Uni<AppUser?>? {
        return findByUsername(username).onItem().ifNotNull().invoke { user ->
            delete(user!!)
        }
    }

}