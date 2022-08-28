package com.knitting.kneedle.core.domain.exception

import com.knitting.kneedle.common.exception.DomainException

class InvalidGauge : DomainException(
    message = "stitches and rows must be greater than 0."
)
