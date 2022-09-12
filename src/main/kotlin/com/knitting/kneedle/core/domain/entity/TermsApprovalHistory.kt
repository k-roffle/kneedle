package com.knitting.kneedle.core.domain.entity

import com.knitting.kneedle.core.domain.value.Terms
import java.time.OffsetDateTime

/**
 * 약관 동의 내역
 *
 * @property id 식별자
 * @property knitterId 동의한 계정
 * @property approvedTermsKey 동의한 약관 key
 * @property createdAt 동의한 시각
 */
data class TermsApprovalHistory(
    val id: String,
    val knitterId: String,
    val approvedTermsKey: Terms,
    val action: Action,
    val createdAt: OffsetDateTime,
) {
    enum class Action {
        APPROVE,
        CANCEL,
    }

    companion object {
        fun create(knitterId: String, approvedTermsKey: Terms) =
            TermsApprovalHistory(
                id = "",
                knitterId = knitterId,
                approvedTermsKey = approvedTermsKey,
                action = Action.APPROVE,
                createdAt = OffsetDateTime.now(),
            )
    }
}
