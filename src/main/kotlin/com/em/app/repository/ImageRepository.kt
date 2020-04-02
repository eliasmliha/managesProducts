package com.em.app.repository

import com.em.app.model.Image
import org.springframework.data.repository.CrudRepository

interface ImageRepository : CrudRepository<Image, Long> {
}