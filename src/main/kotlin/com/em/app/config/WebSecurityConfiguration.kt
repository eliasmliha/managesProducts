package com.em.app.config

import com.em.app.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
class WebSecurityConfiguration(private val passwordEncoderConfig: PasswordEncoder,
                               private val customUserDetailsService: CustomUserDetailsService) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {


        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/").permitAll()

                .antMatchers("/products", "/product").hasAnyRole("USER", "ADMIN")

                .antMatchers("/images", "/image", "/accounts", "/account", "/roles", "role", "privilege", "privileges")
                .hasRole("ADMIN")

                .anyRequest().authenticated()

                .and()
                .httpBasic()


    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailsService)
    }
}
