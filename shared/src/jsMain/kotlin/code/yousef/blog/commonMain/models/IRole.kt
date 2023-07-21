package code.yousef.blog.models

import com.benasher44.uuid.Uuid

interface IRole {
    var id: Uuid?
    var name: RoleName
}
