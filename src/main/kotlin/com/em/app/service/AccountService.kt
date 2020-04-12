package com.em.app.service

import com.em.app.model.Account
import com.em.app.repository.AccountRepository
import javassist.NotFoundException
import org.springframework.stereotype.Service

@Service
class AccountService(val accountRepository: AccountRepository) {


    fun getAllAccount(): MutableIterable<Account> {
        return accountRepository.findAll()
    }

    fun getOneAccount(accountId: Int): Account {
        return (accountRepository.findById(accountId).map { it }.orElse(null)
                ?: throw NotFoundException("Account $accountId does not exist"))
    }

    fun saveAccount(account: Account): Account {
        return accountRepository.save(account)
    }

    fun updateAccount(accountId: Int, newAccount: Account): Account {
        return accountRepository.findById(accountId).map {
            val updatedAccount: Account =
                    Account().apply {
                        firstName = newAccount.firstName
                        lastName = newAccount.lastName
                        userName = newAccount.userName
                        email = newAccount.email
                        passWord = newAccount.passWord
                        accountNonExpired = newAccount.accountNonExpired
                        accountNonLocked = newAccount.accountNonLocked
                        credentialsNonExpired = newAccount.credentialsNonExpired
                        enabled = newAccount.enabled
                        tokenExpired = newAccount.tokenExpired
                    }
            accountRepository.save(updatedAccount)
        }.orElse(null) ?: throw NotFoundException("Account $accountId does not exist")
    }

    fun deleteAccount(accountId: Int) {
        return accountRepository.findById(accountId).map {
            accountRepository.delete(it)
        }.orElse(null) ?: throw NotFoundException("Account $accountId does not exist")
    }
}