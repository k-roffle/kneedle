package com.knitting.kneedle.core.domain.entity

import java.time.OffsetDateTime

class Knitter(
    val id: String,
    val email: String,
    val name: String?,
    val profileImageUrl: String?,
    val createdAt: OffsetDateTime?,
)
