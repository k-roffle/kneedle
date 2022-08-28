package com.knitting.kneedle.core.controller.helper.response

import com.knitting.kneedle.common.config.Loggable
import com.knitting.kneedle.common.exception.BaseException
import com.knitting.kneedle.core.controller.helper.response.type.MetaData
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

object ResponseHelper : Loggable {
    data class Response<T>(
        val payload: T,
        val meta: MetaData,
    )

    suspend fun <T> withJsonResponse(process: suspend () -> T): Response<T> {
        val result = try {
            process()
        } catch (exception: BaseException) {
            throw ResponseStatusException(exception.httpStatus, exception.message)
        } catch (exception: IllegalArgumentException) {
            log.error(exception.stackTraceToString())
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                exception.message ?: "Check your request format."
            )
        } catch (exception: Exception) {
            log.error(exception.stackTraceToString())
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unknown exception raised"
            )
        }
        return Response(
            payload = result,
            meta = MetaData(),
        )
    }
}
