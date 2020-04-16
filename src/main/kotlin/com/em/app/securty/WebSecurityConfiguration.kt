package com.em.app.securty

import com.em.app.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import javax.crypto.SecretKey

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration @Autowired constructor(private val customUserDetailsService: CustomUserDetailsService, private val secretKey: SecretKey
) : WebSecurityConfigurerAdapter() { override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(JwtUsernameAndPasswordAuthenticationFilter(
                        authenticationManager(),
                        secretKey))
                .addFilterAfter(JwtTokenVerifier(secretKey), JwtUsernameAndPasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/products").hasAnyRole("USER", "ADMIN")
                .antMatchers("/images", "/image", "/accounts", "/account", "/roles", "role", "privilege", "privileges").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailsService)
    }

}