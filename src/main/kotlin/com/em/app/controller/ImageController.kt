package com.em.app.controller

import com.em.app.model.Image
import com.em.app.service.ImageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("image")
class ImageController(private val imageService: ImageService) {
    @GetMapping()
    fun getAllImages(): MutableIterable<Image?> {
        return imageService.getAllImage()
    }

    @PostMapping()
    fun createNewImage(@Valid @RequestBody image: Image): Image {
        return imageService.saveImage(image)
    }

    @GetMapping("/{id}")
    fun getImageById(@PathVariable(value = "id") imageId: Long): ResponseEntity<Image>? {
        return imageService.getOneImage(imageId)?.let { ResponseEntity.ok(it) }
    }


    @PutMapping("/{id}")
    fun updateImageById(@PathVariable(value = "id") imageId: Long,
                        @Valid @RequestBody newImage: Image): ResponseEntity<Image>? {
        return imageService.updateImage(imageId, newImage).let { ResponseEntity.ok(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteImageById(@PathVariable(value = "id") imageId: Long): ResponseEntity.BodyBuilder {
        return imageService.deleteImage(imageId).let { ResponseEntity.ok() }
    }
}