package com.em.managesProducts.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @get: NotBlank
        val  title : String = "",
        @get: NotBlank
        val  subtitle : String = "",
        @get: NotBlank
        val price : Double,
        @get: NotBlank
        val description : String = "",
        @get: NotBlank
        val ratings : Int ,
        @get: NotBlank
        val images : String = "")
