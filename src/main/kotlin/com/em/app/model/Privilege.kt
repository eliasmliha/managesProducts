package com.em.app.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
data class Privilege(val name: String = "") {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null

    @ManyToMany(mappedBy = "privileges")
//    @JsonBackReference
    var roles: MutableList<Role>? = mutableListOf()
}