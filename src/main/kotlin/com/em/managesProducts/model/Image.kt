package com.em.managesProducts.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Image(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @NotBlank
        val url: String,
        @ManyToOne
        val product: Product
)