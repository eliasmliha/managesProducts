package com.em.managesProducts.repository

import com.em.managesProducts.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "api/products")
interface ProductRepository : CrudRepository<Product, Long>