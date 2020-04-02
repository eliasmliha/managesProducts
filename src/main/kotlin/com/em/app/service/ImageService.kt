package com.em.app.service

import com.em.app.model.Image
import com.em.app.repository.ImageRepository
import javassist.NotFoundException

class ImageService(private val imageRepository: ImageRepository) {


    fun getAllImage(): MutableIterable<Image> {
        return imageRepository.findAll();
    }

    fun getOneImage(imageId: Long): Image? {
        return imageRepository.findById(imageId).map { it }.orElse(null)
                ?: throw NotFoundException("Image $imageId does not exist")
    }

    fun saveImage(image: Image): Image {
        return imageRepository.save(image)
    }



    fun updateImage(imageId: Long, newImage: Image): Image {
        return imageRepository.findById(imageId).map {
            val updatedImage: Image =
                    it.copy(
                            url = newImage.url,
                            product = newImage.product
                    )
            imageRepository.save(updatedImage)
        }.orElse(null) ?: throw NotFoundException("Image $imageId does not exist")
    }


    fun deleteImage(imageId: Long) {
        return imageRepository.findById(imageId).map {
            imageRepository.delete(it)
        }.orElse(null) ?: throw NotFoundException("Image $imageId does not exist")
    }

}