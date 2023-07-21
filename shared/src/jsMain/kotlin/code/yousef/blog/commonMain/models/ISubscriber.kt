package code.yousef.blog.models

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime

interface ISubscriber {
    var id: Uuid
    var email: String
    var subscriptionDate: LocalDateTime?
    var unsubscribeToken: Uuid
}