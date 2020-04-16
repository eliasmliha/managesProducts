package com.em.app.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

open class CustomUserDetails(user: Account) : Account(user), UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return getGrantedAuthorises()
    }

    override fun getPassword(): String {
        return passWord
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return credentialsNonExpired
    }

    override fun isAccountNonExpired(): Boolean {
        return accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return accountNonLocked

    }


    private fun getGrantedAuthorises(): Collection<GrantedAuthority> {
        val authority: MutableSet<GrantedAuthority> = mutableSetOf()
        roles.forEach {
            authority.add(SimpleGrantedAuthority(it?.roleName))
            it?.privileges?.forEach { authority.add(SimpleGrantedAuthority(it.name)) }
        }
        return authority
    }



}