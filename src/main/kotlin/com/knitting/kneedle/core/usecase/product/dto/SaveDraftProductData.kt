package com.knitting.kneedle.core.usecase.product.dto

data class SaveDraftProductData(
    val id: String?,
    val knitterId: String,
    val productId: String?,
    val value: String,
)
