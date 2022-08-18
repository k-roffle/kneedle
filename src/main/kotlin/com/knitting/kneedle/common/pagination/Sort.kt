package com.knitting.kneedle.common.pagination

import org.springframework.data.domain.Sort as DataSort

data class Sort(
    val column: String,
    val direction: SortDirection,
) {
    fun makeSort(): DataSort {
        val sort = DataSort.by(this.column)
        return when (this.direction) {
            SortDirection.ASC -> sort.ascending()
            SortDirection.DESC -> sort.descending()
        }
    }
}
