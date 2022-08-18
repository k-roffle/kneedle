package com.knitting.kneedle.common.config

import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer

@Configuration
class WebConfig(
    private val authenticatedResolver: AuthenticatedResolver,
) : WebFluxConfigurer {
    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.addCustomResolver(authenticatedResolver)
    }
}
