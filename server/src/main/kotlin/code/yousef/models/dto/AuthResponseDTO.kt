package code.yousef.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDTO(
    val token: String
)