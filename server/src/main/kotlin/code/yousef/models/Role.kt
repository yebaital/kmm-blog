package code.yousef.models

import code.yousef.blog.shared.common.main.models.IRole
import code.yousef.blog.shared.common.main.models.enums.RoleName
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntityBase
import io.quarkus.security.jpa.RolesValue
import jakarta.persistence.*
import kotlinx.serialization.Serializable
import org.hibernate.annotations.GenericGenerator

@Entity
@Serializable
data class Role(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    override var id: String? = null,

    @Column(unique = true)
    @RolesValue
    override var name: RoleName? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    var users: List<AppUser>? = null
) : IRole, PanacheEntityBase
