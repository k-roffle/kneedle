package com.knitting.kneedle.core.domain.entity

import com.knitting.kneedle.core.domain.helper.DraftValueReader
import java.time.OffsetDateTime

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
