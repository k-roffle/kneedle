package com.knitting.kneedle.core.controller.auth.dto

import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload

object RefreshToken {
    data class Response(
        val token: String,
    ) : ObjectPayload
}
