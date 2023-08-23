package code.yousef.models.dto

import code.yousef.blog.shared.common.main.models.dto.IChangePasswordDTO

data class ChangePasswordDTO(
    override val oldPassword: String,
    override val newPassword: String,
    override val verifyPassword: String,
) : IChangePasswordDTO
