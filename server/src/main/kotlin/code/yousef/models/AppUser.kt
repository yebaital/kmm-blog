package code.yousef.models

import code.yousef.blog.shared.common.main.models.IAppUser
import code.yousef.utilities.InstantSerializer
import com.fasterxml.jackson.annotation.JsonIgnore
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntityBase
import io.quarkus.security.jpa.Password
import io.quarkus.security.jpa.Roles
import io.quarkus.security.jpa.UserDefinition
import io.quarkus.security.jpa.Username
import jakarta.persistence.*
import kotlinx.serialization.Serializable
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.Instant

@Entity
@Serializable
@UserDefinition
data class AppUser(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    override var id: String? = null,

    @Column(unique = true)
    @Username
    override var username: String? = null,

    @Column(unique = true)
    override var email: String? = null,

    @JsonIgnore
    @Password
    override var password: String? = null,

    override var firstName: String? = null,
    override var lastName: String? = null,

    @CreationTimestamp
    @Serializable(with = InstantSerializer::class)
    var createdAt: Instant? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Serializable(with = InstantSerializer::class)
    var lastLoggedIn: Instant? = null,

    override var isEnabled: Boolean? = null,

    @ManyToMany(fetch = FetchType.EAGER)
    @Roles
    var roles: Set<Role>? = null
) : IAppUser, PanacheEntityBase
