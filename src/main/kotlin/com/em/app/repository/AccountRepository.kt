package com.em.app.repository

import com.em.app.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@RepositoryRestResource
interface AccountRepository : PagingAndSortingRepository<Account, Int> {
    fun findOneByUserName(userName: String): Account?
}