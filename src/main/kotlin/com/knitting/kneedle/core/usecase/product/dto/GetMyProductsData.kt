package com.knitting.kneedle.core.usecase.product.dto

import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.common.pagination.Sort

data class GetMyProductsData(
    val knitterId: String,
    val paging: Paging,
    val sort: Sort,
)
