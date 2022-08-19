package com.knitting.kneedle.core.controller.knitter.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload

object SalesSummary {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Response(
        val numberOfProductsOnSales: Int,
        val numberOfProductsSold: Int,
    ) : ObjectPayload
}
