package com.knitting.kneedle.core.controller.auth.mapper

import com.knitting.kneedle.core.controller.auth.dto.Authorized
import com.knitting.kneedle.core.controller.auth.dto.RefreshToken

object LoginResponseMapper {
    fun toAuthorizedResponse(token: String): Authorized.Response =
        Authorized.Response(token)

    fun toRefreshTokenResponse(token: String): RefreshToken.Response =
        RefreshToken.Response(token)
}
