package com.knitting.kneedle.core.infra.oauth.exception

import com.knitting.kneedle.common.exception.InfraException
import org.springframework.http.HttpStatus

class InvalidGoogleCode : InfraException(message = "login code is invalid") {
    override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
}
