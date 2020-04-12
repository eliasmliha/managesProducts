package com.em.app.repository

import com.em.app.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository


@RepositoryRestResource
interface ProductRepository : PagingAndSortingRepository<Product, Long> {
    fun findFirstByOrderByIdDesc(): Product
}

