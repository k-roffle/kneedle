package com.knitting.kneedle.core.controller.helper.auth

import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthenticatedResolver(private val tokenDecoder: TokenDecoder) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(Authenticated::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange,
    ): Mono<Any> {
        val authenticatedAnnotations = parameter
            .parameterAnnotations
            .find { it.annotationClass == Authenticated::class } as Authenticated

        val authorizationHeader = resolveKnitterId(exchange.request.headers)

        if (authorizationHeader.isNullOrEmpty()) {
            if (authenticatedAnnotations.required) {
                throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
            }
            return Mono.empty()
        }

        return Mono.just(AuthenticatedUser(authorizationHeader))
    }

    private fun resolveKnitterId(requestHeaders: HttpHeaders): String? {
        val authorizationHeaders = requestHeaders[HttpHeaders.AUTHORIZATION]
        if (authorizationHeaders.isNullOrEmpty()) return null
        val bearerToken = authorizationHeaders.first().toString()
        return if (bearerToken.startsWith(HEADER_PREFIX)) {
            val token = bearerToken.substring(HEADER_PREFIX.length)
            tokenDecoder.getKnitterId(token)
        } else null
    }

    interface TokenDecoder {
        fun getKnitterId(token: String): String
    }

    companion object {
        private const val HEADER_PREFIX = "Bearer "
    }
}
