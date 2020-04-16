package com.em.app

import com.em.app.model.*
import com.em.app.repository.*
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.awt.print.Book
import kotlin.random.Random


@Component
class FakeDataGenerator(val productRepository: ProductRepository,
                        val imageRepository: ImageRepository,
                        val accountRepository: AccountRepository,
                        val roleRepository: RoleRepository,
                        val privilegeRepository: PrivilegeRepository,
                        val passwordEncoder: PasswordEncoder,
                        val restConfiguration: RepositoryRestConfiguration) : CommandLineRunner {


    override fun run(vararg args: String?) {

        restConfiguration.exposeIdsFor(
                Product::class.java,
                Image::class.java,
                Account::class.java,
                Role::class.java,
                Privilege::class.java
        )

        val readPrivilege: Privilege = createPrivilegeIfNotFound("READ_PRIVILEGE")
        val writePrivilege: Privilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE")

        val adminPrivileges: MutableSet<Privilege> = mutableSetOf(readPrivilege, writePrivilege)
        val userPrivileges: MutableSet<Privilege> = mutableSetOf(readPrivilege)

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges)
        createRoleIfNotFound("ROLE_USER", userPrivileges)

        val adminRole: Role? = roleRepository.findByRoleName("ROLE_ADMIN")
        val userRole: Role? = roleRepository.findByRoleName("ROLE_USER")

        val account = Account(
                "admin",
                "admin",
                "admin",
                "admin@admin.com",
                passwordEncoder.encode("admin"))
        account.roles = mutableSetOf(adminRole)
        createAccountIfNotFound(account)

        for (i in 1..4) {
            val account = Account(
                    "user$i",
                    "user$i",
                    "user$i",
                    "user$i@user$i.com",
                    passwordEncoder.encode("user$i"))
            account.roles = mutableSetOf(userRole)
            createAccountIfNotFound(account)
        }

        for (i in 1..30) {
            val product = Product(0, "title $i", "subtitle $i", i.toDouble(), "description $i", Random.nextInt(3, 10))
            productRepository.save(product)
            for (j in 1..Random.nextInt(2, 5)) {
                imageRepository.save(Image(0, "https://picsum.photos/${Random.nextInt(1, 100)}/237/200/300", product))
            }
        }

    }

    private fun createPrivilegeIfNotFound(name: String): Privilege {
        privilegeRepository.findByName(name)?.let { return it }
        return privilegeRepository.save(Privilege(name))

    }


    private fun createRoleIfNotFound(name: String, privileges: MutableSet<Privilege>): Role? {

        roleRepository.findByRoleName(name)?.let { return it }
        val role = Role(name)
        role.privileges = privileges
        return roleRepository.save(role)
    }

    private fun createAccountIfNotFound(account: Account): Account {
        accountRepository.findOneByUserName(account.userName)?.let { return it }
        return accountRepository.save(account)

    }


}