package com.em.app.repository

import com.em.app.model.Image
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface ImageRepository : PagingAndSortingRepository<Image, Long> {
}