package com.em.app.service

import com.em.app.model.Product
import com.em.app.repository.ProductRepository
import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProduct(): MutableIterable<Product> {
        return productRepository.findAll();
    }

    fun getOneProduct(productId: Long): Product? {
        return productRepository.findById(productId).map { it }.orElse(null)
                ?: throw NotFoundException("Product $productId does not exist")
    }

    fun saveProduct(product: Product): Product {
        return productRepository.save(product)
    }



    fun updateProduct(productId: Long, newProduct: Product): Product {
        return productRepository.findById(productId).map {
            val updatedProduct: Product =
                    it.copy(
                            title = newProduct.title,
                            subtitle = newProduct.subtitle,
                            price = newProduct.price,
                            description = newProduct.description,
                            ratings = newProduct.ratings,
                            images = newProduct.images
                    )
            productRepository.save(updatedProduct)
        }.orElse(null) ?: throw NotFoundException("Product $productId does not exist")
    }


    fun deleteProduct(productId: Long) {
        return productRepository.findById(productId).map {
            productRepository.delete(it)
        }.orElse(null) ?: throw NotFoundException("Product $productId does not exist")
    }

}