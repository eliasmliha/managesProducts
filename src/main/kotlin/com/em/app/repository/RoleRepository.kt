package com.em.app.repository

import com.em.app.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Id

@RepositoryRestResource
interface RoleRepository : PagingAndSortingRepository<Role, Int> {
    fun findByRoleName(roleName: String): Role?
    fun findOneById(id: Int): Role
}