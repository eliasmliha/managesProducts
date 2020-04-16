package com.em.app.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import javax.persistence.*


@Entity
open class Account(var firstName: String = "",
                   var lastName: String = "",
                   @Column(unique = true) var userName: String = "",
                   var email: String = "",
                   var passWord: String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    var accountNonExpired: Boolean = true
    var accountNonLocked: Boolean = true
    var credentialsNonExpired: Boolean = true
    var enabled: Boolean = true
    var tokenExpired: Boolean = false

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = [JoinColumn(name = "account_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
//    @JsonManagedReference
    var roles: MutableSet<Role?> = mutableSetOf()


    constructor(account: Account) : this(account.firstName, account.lastName, account.userName, account.email, account.passWord) {
        id = account.id
        firstName = account.firstName
        lastName = account.lastName
        userName = account.userName
        email = account.email
        passWord = account.passWord
        accountNonExpired = account.accountNonExpired
        accountNonLocked = account.accountNonLocked
        credentialsNonExpired = account.credentialsNonExpired
        enabled = account.enabled
        roles = account.roles
        tokenExpired = account.tokenExpired
    }


//    @Transient
//    fun getGrantedAuthorises(): Collection<GrantedAuthority> {
//        val authority: MutableSet<GrantedAuthority> = mutableSetOf()
//        roles.forEach { it?.getGrantedPermission()?.let { authority.addAll(it) } }
//        return authority
//    }

}
