package com.knitting.kneedle.core.infra.oauth.exception

import com.knitting.kneedle.common.exception.InfraException
import org.springframework.http.HttpStatus

class UnavailableGoogle : InfraException(message = "google server is not responding") {
    override val httpStatus: HttpStatus = HttpStatus.SERVICE_UNAVAILABLE
}
