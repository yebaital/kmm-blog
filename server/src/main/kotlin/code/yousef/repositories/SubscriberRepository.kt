package code.yousef.repositories

import code.yousef.models.Subscriber
import com.benasher44.uuid.Uuid
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import io.quarkus.panache.common.Page
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SubscriberRepository : PanacheRepository<Subscriber> {
    suspend fun findByUnsubscribeToken(token: Uuid): Uni<Subscriber?> = find("unsubscribetoken", token).firstResult()

    suspend fun findAll(page: Int): Uni<List<Subscriber>> {
        val subscribers: PanacheQuery<Subscriber> = find("isactive =?1 order by subscriptiondate DESC", true)
        return subscribers.page(Page.of(page, 10)).list()
    }
}