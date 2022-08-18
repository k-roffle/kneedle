package com.knitting.kneedle.core.infra.jwt.exception

import com.knitting.kneedle.common.exception.InfraException
import org.springframework.http.HttpStatus

class UnauthorizedTokenException : InfraException(message = "Token is unauthorized.") {
    override val httpStatus: HttpStatus = HttpStatus.UNAUTHORIZED
}
