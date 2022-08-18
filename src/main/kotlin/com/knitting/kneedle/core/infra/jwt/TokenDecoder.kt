package com.knitting.kneedle.core.infra.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.Claim
import com.knitting.kneedle.common.config.JwtConfigProperties
import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedResolver
import com.knitting.kneedle.core.infra.jwt.exception.ExpiredTokenException
import com.knitting.kneedle.core.infra.jwt.exception.InvalidBodyTokenException
import com.knitting.kneedle.core.infra.jwt.exception.UnauthorizedTokenException
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

@Component
@EnableConfigurationProperties(JwtConfigProperties::class)
class TokenDecoder(
    private val jwtConfigProperties: JwtConfigProperties,
) : AuthenticatedResolver.TokenDecoder {
    override fun getKnitterId(token: String): String {
        val claims = this.decodeToken(token)
        val id = claims["id"] ?: throw InvalidBodyTokenException()
        return id.asString()
    }

    private fun decodeToken(token: String): Map<String, Claim> {
        try {
            val jwt = JWT.require(Algorithm.HMAC256(jwtConfigProperties.secretKey))
                .build()
                .verify(token)
            return jwt.claims
        } catch (error: TokenExpiredException) {
            throw ExpiredTokenException()
        } catch (error: JWTVerificationException) {
            throw UnauthorizedTokenException()
        }
    }
}
