package com.em.app.securty

import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.crypto.SecretKey

@Configuration
class JwtSecretKey {
    @Bean
    fun secretKey(): SecretKey {
        return Keys.hmacShaKeyFor("NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcm.l0aWVzIjpbeyJhdXRob3JpdHkiOiJSRUFEX1.BSSVZJTEVHRSJ9LHsiYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9LHsiYXV0aG9yaXR5IjoiV1JJVEVfUFJJVklMRUdFIn1dLCJpYXQiOjE1ODcwMDMzOTksImV4cCI6MTU4NzI1MDgwMH0.XSX".toByteArray())
    }
}