package com.knitting.kneedle.core.controller.product.dto

import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload
import java.time.OffsetDateTime

object GetMyDraftProduct {
    data class Response(
        val id: String,
        val value: String,
        val updatedAt: OffsetDateTime,
    ) : ObjectPayload
}
