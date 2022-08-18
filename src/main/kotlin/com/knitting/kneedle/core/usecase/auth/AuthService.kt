package com.knitting.kneedle.core.usecase.auth

import com.knitting.kneedle.core.domain.entity.Knitter
import com.knitting.kneedle.core.usecase.auth.input.OAuthProfile
import org.springframework.stereotype.Service
import java.net.URI

@Service
class AuthService(
    private val oAuthHelper: OAuthHelper,
    private val tokenPublisher: TokenPublisher,
    private val knitterRepository: KnitterRepository,
) {
    fun getAuthorizationUri(): URI = oAuthHelper.getAuthorizationUri()

    suspend fun authorize(code: String): String {
        val profile = oAuthHelper.getProfile(code)
        val account = knitterRepository.findByEmail(profile.email)
            ?: createNewAccount(profile)
        return tokenPublisher.publish(account.id)
    }

    fun refreshToken(knitterId: String): String {
        return tokenPublisher.publish(knitterId)
    }

    private fun createNewAccount(profile: OAuthProfile): Knitter {
        val account = with(profile) {
            Knitter(
                id = "",
                email = email,
                name = name,
                profileImageUrl = profileImageUrl,
                createdAt = null,
            )
        }
        return knitterRepository.create(account)
    }

    interface OAuthHelper {
        fun getAuthorizationUri(): URI
        suspend fun getProfile(code: String): OAuthProfile
    }

    interface TokenPublisher {
        fun publish(id: String): String
    }

    interface KnitterRepository {
        fun create(user: Knitter): Knitter
        fun findByEmail(email: String): Knitter?
    }
}
