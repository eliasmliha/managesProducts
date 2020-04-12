package com.em.app.service

import com.em.app.model.Role
import com.em.app.repository.RoleRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service

@Service
class RoleService(val roleRepository: RoleRepository) {

    fun getAllRole(): MutableIterable<Role> {
        return roleRepository.findAll()
    }

    fun getOneRole(roleId: Int): Role? {
        return (roleRepository.findById(roleId).map { it }.orElse(null)
                ?: throw NotFoundException("Role $roleId does not exist"))
    }

    fun saveRole(role: Role): Role {
        return roleRepository.save(role)
    }

    fun updateRole(roleId: Int, newRole: Role): Role {
        return roleRepository.findById(roleId).map {
            val updatedRole = it.copy(
                    roleName = newRole.roleName
            )
            roleRepository.save(updatedRole)
        }.orElse(null) ?: throw NotFoundException("Role $roleId does not exist")
    }

    fun deleteRole(roleId: Int) {
        return roleRepository.findById(roleId).map {
            roleRepository.delete(it)
        }.orElse(null) ?: throw NotFoundException("Role $roleId does not exist")
    }
}