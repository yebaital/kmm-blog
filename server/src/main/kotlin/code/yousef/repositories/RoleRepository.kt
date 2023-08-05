package code.yousef.repositories

import code.yousef.blog.shared.common.main.models.enums.RoleName
import code.yousef.models.Role
import io.quarkus.hibernate.reactive.panache.common.WithSessionOnDemand
import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import java.time.Duration

@ApplicationScoped
@WithSessionOnDemand
class RoleRepository : PanacheRepository<Role> {
    fun findByName(name: RoleName): Uni<Role?> = find("name", name).firstResult()

    @WithTransaction
    fun saveRole(role: Role): Uni<Role> {
        return persistAndFlush(role)
            .replaceWith(role)
            .ifNoItem().after(Duration.ofMillis(10000)).fail()
            .onFailure().transform { t -> IllegalStateException(t) }
            .onItem().transform { it }
    }
}