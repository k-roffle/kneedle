package com.knitting.kneedle.core.infra.jwt.exception

import com.knitting.kneedle.common.exception.InfraException
import org.springframework.http.HttpStatus

class ExpiredTokenException : InfraException(message = "Token is expired.") {
    override val httpStatus: HttpStatus = HttpStatus.UNAUTHORIZED
}
