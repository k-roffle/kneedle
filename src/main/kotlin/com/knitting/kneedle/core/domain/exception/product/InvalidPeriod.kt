package com.knitting.kneedle.core.domain.exception.product

import com.knitting.kneedle.common.exception.DomainException

class InvalidPeriod : DomainException(message = "end date must be greater than start date")
