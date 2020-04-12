package com.em.app.controller

import com.em.app.model.Role
import com.em.app.service.RoleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("role")
class RoleController(private val roleService: RoleService) {
    @GetMapping()
    fun getAllRoles(): MutableIterable<Role?> {
        return roleService.getAllRole()
    }

    @PostMapping()
    fun createNewRole(@Valid @RequestBody role: Role): Role {
        return roleService.saveRole(role)
    }

    @GetMapping("/{id}")
    fun getRoleById(@PathVariable(value = "id") roleId: Int): ResponseEntity<Role>? {
        return roleService.getOneRole(roleId)?.let { ResponseEntity.ok(it) }
    }


    @PutMapping("/{id}")
    fun updateRoleById(@PathVariable(value = "id") roleId: Int,
                       @Valid @RequestBody newRole: Role): ResponseEntity<Role>? {
        return roleService.updateRole(roleId, newRole).let { ResponseEntity.ok(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteRoleById(@PathVariable(value = "id") roleId: Int): ResponseEntity.BodyBuilder {
        return roleService.deleteRole(roleId).let { ResponseEntity.ok() }
    }
}