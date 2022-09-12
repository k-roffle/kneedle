package com.knitting.kneedle.core.domain.entity

/**
 * 약관 동의 내역
 *
 * @property id 식별자
 * @property knitterId 동의한 계정
 * @property approvedTermsKey 동의한 약관 key 목록
 */
data class TermsApprovalHistory(
    val id: String,
    val knitterId: String,
    val approvedTermsKey: List<String>,
)
