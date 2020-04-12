package com.em.app.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
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
        @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JsonManagedReference
        var images: MutableList<Image?> = mutableListOf()
)
