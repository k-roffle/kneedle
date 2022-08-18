package com.knitting.kneedle.core.controller.helper.response

import com.knitting.kneedle.core.controller.helper.response.type.MetaData

object ResponseHelper {
    data class Response<T>(
        val payload: T,
        val meta: MetaData,
    )

    suspend fun <T> withJsonResponse(process: suspend () -> T): Response<T> {
        val result = process()
        return Response(
            payload = result,
            meta = MetaData(),
        )
    }
}
