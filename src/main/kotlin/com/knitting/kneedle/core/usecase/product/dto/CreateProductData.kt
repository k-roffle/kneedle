package com.knitting.kneedle.core.usecase.product.dto

import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.ProductTag
import com.knitting.kneedle.core.domain.value.item.ProductItem
import java.time.OffsetDateTime

data class CreateProductData(
    val knitterId: String,
    val name: String,
    val fullPrice: Money,
    val discountPrice: Money,
    val representativeImageUrl: String,
    val specifiedSalesStartedAt: OffsetDateTime?,
    val specifiedSalesEndedAt: OffsetDateTime?,
    val content: String,
    val tags: List<ProductTag>,
    val items: List<ProductItem>,
    val draftId: String?,
)
