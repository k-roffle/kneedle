package com.knitting.kneedle.core.usecase.product.dto

import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.ProductTag
import java.time.OffsetDateTime

data class UpdateProductData(
    val id: String,
    val knitterId: String,
    val discountPrice: Money,
    val specifiedSalesStartedAt: OffsetDateTime?,
    val specifiedSalesEndedAt: OffsetDateTime?,
    val content: String,
    val tags: List<ProductTag>,
    val draftId: String?,
)
