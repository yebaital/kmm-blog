package code.yousef.blog.shared.common.main.models.dto

interface IChangePasswordDTO {
    val oldPassword: String
    val newPassword: String
    val verifyPassword: String
}