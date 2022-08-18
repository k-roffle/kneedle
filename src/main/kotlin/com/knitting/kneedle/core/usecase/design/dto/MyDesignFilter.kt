package com.knitting.kneedle.core.usecase.design.dto

import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.common.pagination.Sort

data class MyDesignFilter(
    val knitterId: String,
    val paging: Paging,
    val sort: Sort,
)
