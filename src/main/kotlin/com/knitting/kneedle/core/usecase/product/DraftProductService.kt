package com.knitting.kneedle.core.usecase.product

import com.knitting.kneedle.core.domain.entity.DraftProduct
import com.knitting.kneedle.core.domain.entity.Product
import com.knitting.kneedle.core.usecase.product.dto.SaveDraftProductData
import org.springframework.stereotype.Service

@Service
class DraftProductService(
    private val draftProductRepository: DraftProductRepository,
    private val productRepository: ProductRepository,
) {
    fun saveDraft(data: SaveDraftProductData): DraftProduct {
        if (data.productId != null) {
            productRepository.getProduct(data.productId, data.knitterId)
        }
        if (data.id == null) {
            return draftProductRepository.create(
                DraftProduct.new(
                    knitterId = data.knitterId,
                    productId = data.productId,
                    value = data.value,
                )
            )
        }

        val mergedDraftProduct = draftProductRepository
            .getDraftProduct(data.id, data.knitterId)
            .merge(data.value)

        return draftProductRepository.update(mergedDraftProduct)
    }

    fun getMyDraftProducts(knitterId: String): List<DraftProduct> =
        draftProductRepository.findDraftProductsToCreate(knitterId)

    fun getMyDraftProduct(draftProductId: String, knitterId: String): DraftProduct =
        draftProductRepository.getDraftProduct(
            id = draftProductId,
            knitterId = knitterId,
        )

    fun getMyDraftProductToUpdate(productId: String, knitterId: String): DraftProduct =
        draftProductRepository.getDraftProductToUpdate(
            productId = productId,
            knitterId = knitterId,
        )

    fun deleteMyDraftProduct(draftProductId: String, knitterId: String): String {
        val draftProduct = draftProductRepository.getDraftProduct(draftProductId, knitterId)
        return draftProductRepository.delete(draftProduct)
    }

    interface DraftProductRepository {
        fun getDraftProduct(id: String, knitterId: String): DraftProduct
        fun findDraftProductsToCreate(knitterId: String): List<DraftProduct>
        fun getDraftProductToUpdate(productId: String, knitterId: String): DraftProduct
        fun create(draftProduct: DraftProduct): DraftProduct
        fun update(draftProduct: DraftProduct): DraftProduct
        fun delete(draftProduct: DraftProduct): String
    }

    interface ProductRepository {
        fun getProduct(id: String, knitterId: String): Product
    }
}
