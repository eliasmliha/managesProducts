package com.em.managesProducts

import com.em.managesProducts.model.Product
import com.em.managesProducts.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import kotlin.random.Random

@SpringBootApplication
class ManagesProductsApplication {

    @Autowired
    private val productRepository: ProductRepository? = null

    @Bean
    fun init() = CommandLineRunner {
		productRepository?.let { it1 -> generateFakeData(it1) }
    }

}

fun main(args: Array<String>) {
    runApplication<ManagesProductsApplication>(*args)
}

fun generateFakeData(productRepository: ProductRepository) {
    for (i in 1..100) {
        productRepository!!.save(
                Product(
                        i.toLong(),
                        "title $i",
                        "subtitle $i",
                        Random.nextDouble(0.0, 100.0),
                        "description $i",
                        Random.nextInt(0, 10),
                        "https://i.picsum.photos/id/$i/200/300.jpg"))
        println("\n start $i")
    }
}