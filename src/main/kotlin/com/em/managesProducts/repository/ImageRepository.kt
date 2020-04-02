package com.em.managesProducts.repository

import com.em.managesProducts.model.Image
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "images")
interface ImageRepository : PagingAndSortingRepository<Image, Long> {
}

