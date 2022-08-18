package com.knitting.kneedle.core.infra.jwt.exception

import com.knitting.kneedle.common.exception.InfraException
import org.springframework.http.HttpStatus

class InvalidBodyTokenException : InfraException(message = "Token had invalid body format.") {
    override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
}
