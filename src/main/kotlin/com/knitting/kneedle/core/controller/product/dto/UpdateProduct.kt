package com.knitting.kneedle.core.controller.product.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload
import java.time.OffsetDateTime

// TODO: 수정 가능한 범위 정하기 with 해선
object UpdateProduct {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(
        val discountPrice: Int,
        val specifiedSalesStartedAt: OffsetDateTime?,
        val specifiedSalesEndedAt: OffsetDateTime?,
        val tags: List<String>,
        val content: String,
        val draftId: String?,
    )

    data class Response(
        val id: String,
    ) : ObjectPayload
}
