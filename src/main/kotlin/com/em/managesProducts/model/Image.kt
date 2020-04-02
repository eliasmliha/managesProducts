package com.em.managesProducts.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Image(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @NotBlank
        @Column(columnDefinition = "TEXT")
        val url: String,
        @ManyToOne(fetch = FetchType.EAGER)
        val product: Product? = null
)