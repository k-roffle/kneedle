package com.knitting.kneedle.core.controller.design.dto

import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload
import java.time.OffsetDateTime

object GetMyDraftDesign {
    data class Response(
        val id: String,
        val value: String,
        val updatedAt: OffsetDateTime,
    ) : ObjectPayload
}
