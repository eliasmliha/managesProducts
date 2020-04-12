package com.em.app.model


import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.persistence.*


@Entity
data class Role(@Column(unique = true) val roleName: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    var accounts: MutableList<Account>? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")])
    @JsonManagedReference
    var privileges: MutableSet<Privilege>? = mutableSetOf()

//    fun getGrantedPermission(): MutableList<SimpleGrantedAuthority>? {
//        val authority: MutableSet<GrantedAuthority> = mutableSetOf()
//        for (privilege in privileges!!) {
//            authority.add(SimpleGrantedAuthority(privilege.name))
//        }
//
//        return privileges?.stream()?.map { SimpleGrantedAuthority(it.name) }?.collect(Collectors.toList())
//    }

}