package com.knitting.kneedle.common.exception

import org.springframework.http.HttpStatus

open class ControllerException(
    override val message: String,
    override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
) : BaseException(message = message) {
    override val layer: ExceptionLayer = ExceptionLayer.CONTROLLER
}
