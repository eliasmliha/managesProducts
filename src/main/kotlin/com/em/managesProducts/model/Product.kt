package com.em.managesProducts.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val  title : String = "",
        val  subtitle : String = "",
        val price : Double = 0.0,
        val description : String = "",
        val ratings : Int  = 0,
        val images : String = "")
