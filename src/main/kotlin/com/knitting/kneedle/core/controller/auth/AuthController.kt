package com.knitting.kneedle.core.controller.auth

import com.knitting.kneedle.core.controller.auth.mapper.LoginResponseMapper
import com.knitting.kneedle.core.controller.helper.auth.Authenticated
import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedUser
import com.knitting.kneedle.core.controller.helper.response.ResponseHelper.withJsonResponse
import com.knitting.kneedle.core.usecase.auth.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("auth")
class AuthController(private val authService: AuthService) {
    @GetMapping("google/code")
    fun requestCode(response: ServerHttpResponse): Mono<Void> {
        response.statusCode = HttpStatus.PERMANENT_REDIRECT
        response.headers.location = authService.getAuthorizationUri()
        return response.setComplete()
    }

    @GetMapping("google/authorized")
    suspend fun authorized(@RequestParam code: String) = withJsonResponse {
        val result = authService.authorize(code)
        LoginResponseMapper.toAuthorizedResponse(result)
    }

    @PostMapping("refresh")
    suspend fun refreshToken(@Authenticated user: AuthenticatedUser) = withJsonResponse {
        val refreshToken = authService.refreshToken(user.knitterId)
        LoginResponseMapper.toRefreshTokenResponse(refreshToken)
    }
}
