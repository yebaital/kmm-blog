package code.yousef.models.dto

import code.yousef.blog.shared.common.main.models.dto.ISignInDTO
import kotlinx.serialization.Serializable

@Serializable
data class SignInDTO(
    override val username: String,
    override val password: String
) : ISignInDTO
