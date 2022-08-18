package com.knitting.kneedle.core.domain.exception.product

import com.knitting.kneedle.common.exception.DomainException

class InvalidFullPrice : DomainException(message = "full price must be greater than 0")
