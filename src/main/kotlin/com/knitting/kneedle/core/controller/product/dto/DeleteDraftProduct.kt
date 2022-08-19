package com.knitting.kneedle.core.controller.product.dto

import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload

object DeleteDraftProduct {
    data class Response(
        val id: String,
    ) : ObjectPayload
}
