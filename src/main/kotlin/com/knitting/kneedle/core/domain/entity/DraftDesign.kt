package com.knitting.kneedle.core.domain.entity

import com.knitting.kneedle.core.domain.helper.DraftValueReader
import java.time.OffsetDateTime

data class DraftDesign(
    val id: String,
    val knitterId: String,
    val value: String,
    val designId: String?,
    val createdAt: OffsetDateTime?,
    val updatedAt: OffsetDateTime?,
) {
    fun merge(value: String): DraftDesign =
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
        fun new(knitterId: String, designId: String?, value: String): DraftDesign =
            DraftDesign(
                id = "",
                knitterId = knitterId,
                designId = designId,
                value = value,
                createdAt = OffsetDateTime.now(),
                updatedAt = OffsetDateTime.now(),
            )
    }
}
