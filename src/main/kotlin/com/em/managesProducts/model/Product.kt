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
        val subtitle: String,
        @NotBlank
        val price: Double,
        @NotBlank
        val description: String,
        @NotBlank
        val ratings: Int,
        @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
        val images: List<Image>)
