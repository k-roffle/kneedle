package com.knitting.kneedle.core.controller.design.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload

object SaveDraftDesign {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(
        val id: String?,
        val designId: String?,
        val value: String,
    )

    data class Response(
        val id: String,
    ) : ObjectPayload
}
