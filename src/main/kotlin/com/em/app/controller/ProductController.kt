package com.em.app.controller

import com.em.app.model.Product
import com.em.app.service.ProductService
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("product")
class ProductController(private val productService: ProductService) {

    @GetMapping()
    fun getAllProducts(pageable: Pageable): MutableIterable<Product> {
        return productService.getAllProduct(pageable)
    }

    @PostMapping()
    fun createNewProduct(@Valid @RequestBody product: Product): Product {
        return productService.saveProduct(product)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable(value = "id") productId: Long): ResponseEntity<Product>? {
        return productService.getOneProduct(productId)?.let { ResponseEntity.ok(it) }
    }


    @PutMapping("/{id}")
    fun updateProductById(@PathVariable(value = "id") productId: Long,
                          @Valid @RequestBody newProduct: Product): ResponseEntity<Product>? {
        return productService.updateProduct(productId, newProduct).let { ResponseEntity.ok(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteProductById(@PathVariable(value = "id") productId: Long): ResponseEntity.BodyBuilder {
        return productService.deleteProduct(productId).let { ResponseEntity.ok() }
    }
}