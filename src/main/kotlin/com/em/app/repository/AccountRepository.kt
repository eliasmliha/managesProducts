package com.em.app.repository

import com.em.app.model.Account
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface AccountRepository : PagingAndSortingRepository<Account, Int> {
    fun findOneByUserName(userName: String): Account?
}