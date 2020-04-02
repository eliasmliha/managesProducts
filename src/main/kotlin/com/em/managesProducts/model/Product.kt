package com.em.managesProducts.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @NotBlank
        val title: String,
        @NotBlank
        @Column(columnDefinition = "TEXT")
        val subtitle: String,
        @NotBlank
        val price: Double,
        @NotBlank
        @Column(columnDefinition = "TEXT")
        val description: String,
        @NotBlank
        val ratings: Int,
        @OneToMany(cascade = [CascadeType.ALL])
        var images: List<Image> = emptyList()
)
