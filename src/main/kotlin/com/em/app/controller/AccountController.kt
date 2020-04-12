package com.em.app.controller

import com.em.app.model.Account
import com.em.app.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController()
@RequestMapping("account")
class AccountController(private val accountService: AccountService) {

    @GetMapping()
    fun getAllAccounts(): MutableIterable<Account> {
        return accountService.getAllAccount()
    }

    @PostMapping()
    fun createNewAccount(@Valid @RequestBody account: Account): Account {
        return accountService.saveAccount(account)
    }

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable(value = "id") accountId: Int): ResponseEntity<Account> {
        return accountService.getOneAccount(accountId).let { ResponseEntity.ok(it) }
    }


    @PutMapping("/{id}")
    fun updateAccountById(@PathVariable(value = "id") accountId: Int,
                          @Valid @RequestBody newAccount: Account): ResponseEntity<Account>? {
        return accountService.updateAccount(accountId, newAccount).let { ResponseEntity.ok(it) }
    }

    @DeleteMapping("/{id}")
    fun deleteAccountById(@PathVariable(value = "id") accountId: Int): ResponseEntity.BodyBuilder {
        return accountService.deleteAccount(accountId).let { ResponseEntity.ok() }
    }

}