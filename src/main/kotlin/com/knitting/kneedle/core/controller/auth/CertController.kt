package com.knitting.kneedle.core.controller.auth

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableConfigurationProperties(CertController.CertProperties::class)
class CertController(
    private val certProperties: CertProperties,
) {
    @GetMapping(".well-known/acme-challenge/Fk4ql_Mo4IDQ9i6AwKS3p2WtrLu5456uHLmP1XKt-ts")
    suspend fun applyHttps(): String = certProperties.secret

    @ConstructorBinding
    @ConfigurationProperties(prefix = "cert")
    data class CertProperties(
        val secret: String,
    )
}
