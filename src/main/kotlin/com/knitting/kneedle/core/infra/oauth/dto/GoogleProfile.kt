package com.knitting.kneedle.core.infra.oauth.dto

object GoogleProfile {
    data class Response(
        val email: String,
        val name: String,
        val picture: String,
    )
}
