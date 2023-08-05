package code.yousef.models.dto

data class ChangePasswordDTO(
    val oldPassword: String,
    val newPassword: String,
    val verifyPassword: String
)
