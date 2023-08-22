package code.yousef.blog.models

import code.yousef.blog.shared.common.main.models.ISignInDTO
import kotlinx.serialization.Serializable

@Serializable
data class SignInDTO(
    override val username: String,
    override val password: String
) : ISignInDTO
