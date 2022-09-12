package com.knitting.kneedle.core.domain.entity

import com.knitting.kneedle.core.domain.value.Account
import java.time.OffsetDateTime

/**
 * 디자이너
 *
 * @property id 식별자
 * @property knitterId knitter id
 * @property nickname 닉네임
 * @property selfIntroduction 소개글
 * @property account 정산 계좌
 * @property createdAt 디자이너 등록 시각
 * @property updatedAt 디자이너 정보 마지막 업데이트 시각
 */
data class Designer(
    val id: String,
    val knitterId: String,
    val nickname: String,
    val selfIntroduction: String,
    val account: Account,
    val createdAt: OffsetDateTime?,
    val updatedAt: OffsetDateTime?,
) {
    companion object {
        fun create(
            knitter: Knitter,
            nickname: String,
            selfIntroduction: String,
            account: Account,
        ) = Designer(
            id = "",
            knitterId = knitter.id,
            nickname = nickname,
            selfIntroduction = selfIntroduction,
            account = account,
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now(),
        )
    }
}
