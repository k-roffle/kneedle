package com.knitting.kneedle.core.usecase.summary

import com.knitting.kneedle.core.usecase.summary.dto.ProfileSummary
import org.springframework.stereotype.Service

@Service
class ProfileSummaryService(
    private val productRepository: ProductRepository,
    private val designRepository: DesignRepository,
) {
    fun getProfileSummary(knitterId: String): ProfileSummary =
        ProfileSummary(
            myProductsCount = productRepository.countMyProducts(knitterId),
            purchasedProductsCount = productRepository.countPurchasedProducts(knitterId),
            myDesignsCount = designRepository.countMyDesigns(knitterId),
        )

    interface ProductRepository {
        fun countMyProducts(knitterId: String): Int
        fun countPurchasedProducts(knitterId: String): Int
    }

    interface DesignRepository {
        fun countMyDesigns(knitterId: String): Int
    }
}
