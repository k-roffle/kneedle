package com.knitting.kneedle.core.controller.product.dto

import com.knitting.kneedle.core.controller.helper.response.type.ListItemPayload
import java.time.OffsetDateTime

object GetMyDraftProducts {
    data class Response(
        val id: String,
        val name: String?,
        val updatedAt: OffsetDateTime,
    ) : ListItemPayload {
        override fun getCursor(): String = this.id
    }
}
