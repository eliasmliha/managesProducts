package com.em.managesProducts.repository

import com.em.managesProducts.model.Image
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "api/images")
interface ImageRepository : CrudRepository<Image, Long> {
}

