package com.knitting.kneedle.core.domain.entity

import java.time.OffsetDateTime

/**
 * 사용자
 *
 * @property id 식별자
 * @property email 가입 이메일
 * @property name 이름
 * @property profileImageUrl 프로필 이미지
 * @property createdAt 가입 시각
 */
class Knitter(
    val id: String,
    val email: String,
    val name: String?,
    val profileImageUrl: String?,
    val createdAt: OffsetDateTime?,
)
