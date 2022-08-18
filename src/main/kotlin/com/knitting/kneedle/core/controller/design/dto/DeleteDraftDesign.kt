package com.knitting.kneedle.core.controller.design.dto

import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload

object DeleteDraftDesign {
    data class Response(
        val id: String,
    ) : ObjectPayload
}
