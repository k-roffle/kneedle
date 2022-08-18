package com.knitting.kneedle.core.infra.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.knitting.kneedle.common.config.JwtConfigProperties
import com.knitting.kneedle.common.extension.toDate
import com.knitting.kneedle.core.usecase.auth.AuthService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
@EnableConfigurationProperties(JwtConfigProperties::class)
class TokenPublisher(
    private val jwtConfigProperties: JwtConfigProperties,
) : AuthService.TokenPublisher {
    override fun publish(id: String): String {
        val now = OffsetDateTime.now()
        val expiredAt = now.plusSeconds(EXPIRATION_TIME)

        return JWT.create()
            .withClaim("id", id)
            .withIssuedAt(now.toLocalDateTime().toDate())
            .withExpiresAt(expiredAt.toLocalDateTime().toDate())
            .sign(Algorithm.HMAC256(jwtConfigProperties.secretKey))
    }

    companion object {
        private const val EXPIRATION_TIME = (60 * 60 * 24).toLong()
    }
}
