package com.knitting.kneedle.common.pagination

import org.springframework.data.domain.PageRequest

object Paginator {
    fun makePageRequest(paging: Paging, sort: Sort): PageRequest = PageRequest.of(
        0,
        paging.count,
        sort.makeSort(),
    )
}
