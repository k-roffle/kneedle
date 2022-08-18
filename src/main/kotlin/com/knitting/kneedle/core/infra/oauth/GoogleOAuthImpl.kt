package com.knitting.kneedle.core.infra.oauth

import com.knitting.kneedle.common.config.GoogleWebClientConfig
import com.knitting.kneedle.core.infra.oauth.dto.GoogleAccessToken
import com.knitting.kneedle.core.infra.oauth.dto.GoogleProfile
import com.knitting.kneedle.core.infra.oauth.exception.InvalidGoogleAccessToken
import com.knitting.kneedle.core.infra.oauth.exception.InvalidGoogleCode
import com.knitting.kneedle.core.infra.oauth.exception.UnavailableGoogle
import com.knitting.kneedle.core.usecase.auth.AuthService
import com.knitting.kneedle.core.usecase.auth.input.OAuthProfile
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Component
class GoogleOAuthImpl(
    @Qualifier("googleOAuthWebClient")
    private val googleOAuthWebClient: WebClient,
    @Qualifier("googleApiWebClient")
    private val googleApiWebClient: WebClient,
    private val googleGCPProperties: GoogleWebClientConfig.Properties.GCPProperties,
    private val googleRedirectUriProperties: GoogleWebClientConfig.Properties.RedirectUriProperties,
    private val googleAuthorizationUriProperties: GoogleWebClientConfig.Properties.AuthorizationProperties,
) : AuthService.OAuthHelper {
    override fun getAuthorizationUri(): URI =
        with(googleAuthorizationUriProperties) {
            URI.create(
                UriComponentsBuilder.newInstance()
                    .scheme(scheme)
                    .host(host)
                    .path(path)
                    .queryParam("scope", scopes.joinToString("+"))
                    .queryParam("access_type", accessType)
                    .queryParam("include_granted_scopes", includeGrantedScopes)
                    .queryParam("response_type", responseType)
                    .queryParam("redirect_uri", getCallbackUri())
                    .queryParam("client_id", googleGCPProperties.clientId)
                    .build()
                    .toUriString()
            )
        }

    override suspend fun getProfile(code: String): OAuthProfile {
        val accessToken = fetchAccessToken(code)
        val profile = googleApiWebClient
            .get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/oauth2/v3/userinfo")
                    .queryParam("access_token", accessToken)
                    .build()
            }
            .retrieve()
            .onStatus(HttpStatus::isError) {
                when (it.statusCode()) {
                    HttpStatus.BAD_REQUEST -> throw InvalidGoogleAccessToken()
                    else -> throw UnavailableGoogle()
                }
            }
            .awaitBody<GoogleProfile.Response>()
        return with(profile) {
            OAuthProfile(
                email = email,
                name = name,
                profileImageUrl = picture,
            )
        }
    }

    private suspend fun fetchAccessToken(code: String): String =
        with(googleGCPProperties) {
            googleOAuthWebClient
                .post()
                .uri("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    mapOf(
                        "code" to code,
                        "client_id" to clientId,
                        "client_secret" to secretKey,
                        "redirect_uri" to getCallbackUri(),
                        "grant_type" to "authorization_code",
                    )
                )
                .retrieve()
                .onStatus(HttpStatus::isError) {
                    when (it.statusCode()) {
                        HttpStatus.BAD_REQUEST -> throw InvalidGoogleCode()
                        else -> throw UnavailableGoogle()
                    }
                }
                .awaitBody<GoogleAccessToken.Response>()
                .accessToken
        }

    private fun getCallbackUri(): String =
        with(googleRedirectUriProperties) {
            UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .path(path)
                .build()
                .toUriString()
        }
}
