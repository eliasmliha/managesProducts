package com.em.app.repository

import com.em.app.model.Privilege
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@RepositoryRestResource
interface PrivilegeRepository : PagingAndSortingRepository<Privilege, Int> {
    fun findByName(name: String): Privilege?
}