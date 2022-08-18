package com.knitting.kneedle.common.config

import io.netty.channel.ChannelOption
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
@EnableConfigurationProperties(GoogleWebClientConfig.Properties::class)
class GoogleWebClientConfig(private val properties: Properties) {

    @Bean
    fun googleOAuthWebClient(builder: WebClient.Builder): WebClient =
        with(properties.oAuthClient) {
            val httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .responseTimeout(Duration.ofMillis(timeout.toLong()))

            return builder.baseUrl(host)
                .clientConnector(ReactorClientHttpConnector(httpClient))
                .build()
        }

    @Bean
    fun googleApiWebClient(builder: WebClient.Builder): WebClient =
        with(properties.apiClient) {
            val httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .responseTimeout(Duration.ofMillis(timeout.toLong()))

            return builder.baseUrl(host)
                .clientConnector(ReactorClientHttpConnector(httpClient))
                .build()
        }

    @Bean
    fun googleGCPProperties(): Properties.GCPProperties = properties.gcp

    @Bean
    fun googleRedirectUriProperties(): Properties.RedirectUriProperties = properties.redirectUri

    @Bean
    fun googleAuthorizationUriProperties(): Properties.AuthorizationProperties = properties.authorizationUri

    @ConstructorBinding
    @ConfigurationProperties(prefix = "google")
    data class Properties(
        val oAuthClient: ClientProperties,
        val apiClient: ClientProperties,
        val gcp: GCPProperties,
        val authorizationUri: AuthorizationProperties,
        val redirectUri: RedirectUriProperties,
    ) {
        data class ClientProperties(
            val host: String,
            val timeout: Int,
        )
        data class GCPProperties(
            val clientId: String,
            val secretKey: String,
        )
        data class AuthorizationProperties(
            val host: String,
            val scheme: String,
            val path: String,
            val scopes: List<String>,
            val accessType: String,
            val includeGrantedScopes: String,
            val responseType: String,
        )
        data class RedirectUriProperties(
            val host: String,
            val scheme: String,
            val path: String,
        )
    }
}
