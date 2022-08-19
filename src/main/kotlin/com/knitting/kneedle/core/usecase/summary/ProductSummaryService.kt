package com.knitting.kneedle.core.usecase.summary

import com.knitting.kneedle.core.domain.entity.Product
import org.springframework.stereotype.Service

@Service
class ProductSummaryService(private val repository: ProductRepository) {
    fun countProductOnList(knitterId: String): Int =
        repository
            .findRegisteredProduct(knitterId)
            .count { it.onList }

    interface ProductRepository {
        fun findRegisteredProduct(knitterId: String): List<Product>
    }
}
