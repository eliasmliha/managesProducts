package com.em.managesProducts

import com.em.managesProducts.model.Image
import com.em.managesProducts.model.Product
import com.em.managesProducts.repository.ImageRepository
import com.em.managesProducts.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.stereotype.Component
import kotlin.random.Random


@Component
class FakeDataGenerator(val productRepository: ProductRepository, val imageRepository: ImageRepository) : CommandLineRunner {
    @Autowired
    private val restConfiguration: RepositoryRestConfiguration? = null


    override fun run(vararg args: String?) {
        restConfiguration!!.exposeIdsFor(Product::class.java, Image::class.java)
        for (i in 1..30) {
            val product = Product(0, "title ${i}", "subtitle ${i}", i.toDouble(), "description ${i}", i + 1)
            productRepository.save(product)
            for (j in 1..Random.nextInt(2, 5)) {
                val image = Image(0, "https://picsum.photos/${Random.nextInt(1, 100)}/237/200/300", product)
                imageRepository.save(image)
            }
        }
    }
}