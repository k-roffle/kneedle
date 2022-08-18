package com.knitting.kneedle.common.exception

import org.springframework.http.HttpStatus

abstract class BaseException(override val message: String) : Exception(message) {
    abstract val httpStatus: HttpStatus
    abstract val layer: ExceptionLayer

    enum class ExceptionLayer {
        DOMAIN,
        USE_CASE,
        CONTROLLER,
        INFRA
    }
}
