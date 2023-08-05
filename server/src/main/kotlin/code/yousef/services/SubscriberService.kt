package code.yousef.services

import code.yousef.repositories.SubscriberRepository
import com.benasher44.uuid.Uuid
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SubscriberService(val subscriberRepository: SubscriberRepository) {

    suspend fun findByUnsubscribeToken(token: Uuid) = subscriberRepository.findByUnsubscribeToken(token)

    suspend fun findAll(page: Int) = subscriberRepository.findAll(page)

}