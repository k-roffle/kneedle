package com.knitting.kneedle.core.controller.product.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload

object UpdateProductSalesStatus {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(val status: Status) {
        enum class Status {
            STOP,
            ON_SALE,
        }
    }

    data class Response(
        val id: String,
    ) : ObjectPayload
}
