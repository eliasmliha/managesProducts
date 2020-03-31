package com.em.app.controller

import com.em.app.model.Product
import com.em.app.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping()
    fun getAllProducts(): MutableIterable<Product> {
        return productService.getAllProduct()
    }

    @PostMapping()
    fun createNewProduct(@Valid @RequestBody product: Product): Product {
        return productService.saveProduct(product)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable(value = "id") productId: Long): ResponseEntity<Product> {
        return productService.getOneProduct(productId)
    }

    @PutMapping("/{id}")
    fun updateProductById(@PathVariable(value = "id") productId: Long,
                          @Valid @RequestBody newProduct: Product): ResponseEntity<Product> {
        return productService.updateProduct(productId, newProduct)
    }

    @DeleteMapping("/{id}")
    fun deleteProductById(@PathVariable(value = "id") productId: Long): ResponseEntity<Void> {
        return productService.deleteProduct(productId)
    }
}