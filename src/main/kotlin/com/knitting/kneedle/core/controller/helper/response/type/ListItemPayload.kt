package com.knitting.kneedle.core.controller.helper.response.type

import com.fasterxml.jackson.annotation.JsonIgnore

interface ListItemPayload : Payload {
    @JsonIgnore
    fun getCursor(): String
}
