package com.em.app.service

import com.em.app.model.Product
import com.em.app.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAllProduct(): MutableIterable<Product> {
        return productRepository.findAll();
    }

    fun getOneProduct(productId: Long): ResponseEntity<Product> {
        return productRepository.findById(productId).map { product ->
            ResponseEntity.ok(product)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun saveProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun updateProduct(productId: Long, newProduct: Product): ResponseEntity<Product> {
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
            ResponseEntity.ok().body(productRepository.save(updatedProduct))
        }.orElse(ResponseEntity.notFound().build())
    }

    fun deleteProduct(productId: Long): ResponseEntity<Void> {
        return productRepository.findById(productId).map { product ->
            productRepository.delete(product)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

}