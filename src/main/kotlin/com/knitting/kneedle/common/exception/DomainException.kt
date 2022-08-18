package com.knitting.kneedle.common.exception

import org.springframework.http.HttpStatus

open class DomainException(override val message: String) : BaseException(message = message) {
    override val layer: ExceptionLayer = ExceptionLayer.DOMAIN
    override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
}
