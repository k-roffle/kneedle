package com.knitting.kneedle.core.domain.exception

import com.knitting.kneedle.common.exception.DomainException

class InvalidLength : DomainException(
    message = "Length must be greater than 0."
)
