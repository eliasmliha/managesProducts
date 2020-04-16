package com.em.app.service

import com.em.app.model.CustomUserDetails
import org.springframework.stereotype.Service
import com.em.app.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

@Service
class CustomUserDetailsService(private val accountRepository: AccountRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return CustomUserDetails(accountRepository.findOneByUserName(username)!!)
    }
}