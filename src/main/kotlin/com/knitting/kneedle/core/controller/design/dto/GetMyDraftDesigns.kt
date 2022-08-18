package com.knitting.kneedle.core.controller.design.dto

import com.knitting.kneedle.core.controller.helper.response.type.ListItemPayload
import java.time.OffsetDateTime

object GetMyDraftDesigns {
    data class Response(
        val id: String,
        val name: String?,
        val updatedAt: OffsetDateTime,
    ) : ListItemPayload {
        override fun getCursor(): String = this.id
    }
}
