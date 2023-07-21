package code.yousef.blog.repositories

import code.yousef.blog.models.ISubscriber
import com.benasher44.uuid.Uuid

interface ISubscriberRepository {
    fun findByUnsubscribeToken(token: Uuid): ISubscriber?
    fun findFirstByOrderBySubscriptionDateDesc(): ISubscriber
}