package code.yousef.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignInDTO(
    val username:String,
    val password:String
)
