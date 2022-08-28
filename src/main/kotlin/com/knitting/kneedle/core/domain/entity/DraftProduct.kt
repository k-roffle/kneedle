package com.knitting.kneedle.core.domain.entity

import com.knitting.kneedle.core.domain.helper.DraftValueReader
import java.time.OffsetDateTime

/**
 * 상품 임시저장
 *
 * @property id 식별자
 * @property knitterId 사용자 id
 * @property value 입력 중인 데이터
 * @property productId 수정하는 대상 상품, null 인 경우 신규 생성을 위한 임시저장 데이터
 * @property createdAt 생성 시각
 * @property updatedAt 수정 시각
 */
data class DraftProduct(
    val id: String,
    val knitterId: String,
    val value: String,
    val productId: String?,
    val createdAt: OffsetDateTime?,
    val updatedAt: OffsetDateTime?,
) {
    fun merge(value: String): DraftProduct =
        this.copy(
            value = value,
            updatedAt = OffsetDateTime.now(),
        )

    val name: String?
        get() = DraftValueReader.read(value, ParsedValue::name)

    data class ParsedValue(
        val name: String? = null,
    ) : DraftValueReader.TruncatedValue()

    companion object {
        fun new(knitterId: String, productId: String?, value: String): DraftProduct =
            DraftProduct(
                id = "",
                knitterId = knitterId,
                productId = productId,
                value = value,
                createdAt = OffsetDateTime.now(),
                updatedAt = OffsetDateTime.now(),
            )
    }
}
