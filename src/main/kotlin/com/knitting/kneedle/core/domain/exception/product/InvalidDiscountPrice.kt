package com.knitting.kneedle.core.domain.exception.product

import com.knitting.kneedle.common.exception.DomainException

class InvalidDiscountPrice : DomainException(message = "discount price must be between 0 and full price")
