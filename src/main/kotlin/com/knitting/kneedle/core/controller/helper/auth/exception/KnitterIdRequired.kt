package com.knitting.kneedle.core.controller.helper.auth.exception

import com.knitting.kneedle.common.exception.ControllerException
import org.springframework.http.HttpStatus

class KnitterIdRequired : ControllerException(message = "knitterId is required", HttpStatus.FORBIDDEN)
