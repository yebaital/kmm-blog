package code.yousef.models.dto

import code.yousef.blog.shared.common.main.models.IAuthResponseDTO
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDTO(
    override val token: String,
) : IAuthResponseDTO