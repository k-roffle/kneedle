package com.knitting.kneedle.core.controller.product.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload
import java.time.OffsetDateTime

object CreateProduct {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(
        val designIds: List<String>,
        val name: String,
        val fullPrice: Int,
        val discountPrice: Int,
        val representativeImageUrl: String,
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
