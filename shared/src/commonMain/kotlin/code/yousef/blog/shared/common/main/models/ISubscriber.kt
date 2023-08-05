package code.yousef.blog.shared.common.main.models

interface ISubscriber {
    var id: String?
    var email: String
    var unsubscribeToken: String?
    var isActive: Boolean
}