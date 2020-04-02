package com.em.managesProducts.repository

import com.em.managesProducts.model.Product
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "products")
interface ProductRepository : PagingAndSortingRepository<Product, Long>