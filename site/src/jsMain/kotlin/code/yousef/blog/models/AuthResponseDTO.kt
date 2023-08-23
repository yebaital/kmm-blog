package code.yousef.blog.models

import code.yousef.blog.shared.common.main.models.dto.IAuthResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDTO(
    @SerialName("token" )
    override val token: String,
) : IAuthResponseDTO
