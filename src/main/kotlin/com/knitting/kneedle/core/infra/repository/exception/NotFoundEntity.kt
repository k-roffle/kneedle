package com.knitting.kneedle.core.infra.repository.exception

import com.knitting.kneedle.common.exception.InfraException
import org.springframework.http.HttpStatus

class NotFoundEntity(clazz: Class<*>) : InfraException(message = "Cannot found ${clazz.name}") {
    override val httpStatus: HttpStatus = HttpStatus.NOT_FOUND
}
