package code.yousef.blog.models

import com.benasher44.uuid.Uuid
import kotlinx.datetime.LocalDateTime

interface IAppUser {
    val id: Uuid?
    var username: String
    var email: String
    var password: String
    var firstName: String
    var lastName: String
    var createdAt: LocalDateTime
    var lastLoggedIn: LocalDateTime
    var isEnabled: Boolean
    var roles: Set<IRole> // Many to many
}

