package com.knitting.kneedle.core.usecase.auth.input

data class OAuthProfile(
    val email: String,
    val name: String,
    val profileImageUrl: String?,
)
