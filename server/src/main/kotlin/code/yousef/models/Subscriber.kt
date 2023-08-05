package code.yousef.models

import code.yousef.blog.shared.common.main.models.ISubscriber
import code.yousef.utilities.InstantSerializer
import com.benasher44.uuid.Uuid
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import kotlinx.serialization.Serializable
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.Instant

@Entity
@Serializable
data class Subscriber(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    override var id: String?,

    override var email: String,

    @CreationTimestamp
    @Serializable(with = InstantSerializer::class)
    var subscriptionDate: Instant?,

    override var unsubscribeToken: String? = Uuid.randomUUID().toString(),
    override var isActive: Boolean,

) : ISubscriber, PanacheEntityBase
