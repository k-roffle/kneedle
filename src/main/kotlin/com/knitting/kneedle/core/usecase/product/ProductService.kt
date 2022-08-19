package com.knitting.kneedle.core.usecase.product

import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.common.pagination.Sort
import com.knitting.kneedle.core.domain.entity.DraftProduct
import com.knitting.kneedle.core.domain.entity.Product
import com.knitting.kneedle.core.usecase.product.dto.CreateProductData
import com.knitting.kneedle.core.usecase.product.dto.GetMyProductData
import com.knitting.kneedle.core.usecase.product.dto.GetMyProductsData
import com.knitting.kneedle.core.usecase.product.dto.UpdateProductData
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val draftProductRepository: DraftProductRepository,
) {
    private fun deleteMyDraftProduct(draftId: String?, knitterId: String) {
        draftId ?: return
        val draftProduct = draftProductRepository.getDraftProduct(id = draftId, knitterId = knitterId)
        draftProductRepository.delete(draftProduct)
    }

    fun create(data: CreateProductData): Product {
        val product = with(data) {
            Product.new(
                knitterId = knitterId,
                name = name,
                fullPrice = fullPrice,
                discountPrice = discountPrice,
                representativeImageUrl = representativeImageUrl,
                specifiedSalesStartedAt = specifiedSalesStartedAt,
                specifiedSalesEndedAt = specifiedSalesEndedAt,
                content = content,
                tags = tags,
                items = items,
            )
        }
        val createdProduct = productRepository.createProduct(product)
        deleteMyDraftProduct(data.draftId, data.knitterId)
        return createdProduct
    }

    fun update(data: UpdateProductData): Product {
        val product = with(data) {
            productRepository
                .getProduct(data.id, data.knitterId)
                .update(
                    discountPrice = discountPrice,
                    specifiedSalesStartedAt = specifiedSalesStartedAt,
                    specifiedSalesEndedAt = specifiedSalesEndedAt,
                    content = content,
                    tags = tags,
                )
        }
        val updatedProduct = productRepository.updateProduct(product)
        deleteMyDraftProduct(data.draftId, data.knitterId)
        return updatedProduct
    }

    fun get(data: GetMyProductData): Product =
        productRepository
            .getProduct(data.id, data.knitterId)

    fun get(data: GetMyProductsData): List<Product> =
        productRepository
            .getProductsByKnitterId(data.knitterId, data.paging, data.sort)

    interface ProductRepository {
        fun createProduct(product: Product): Product
        fun updateProduct(product: Product): Product
        fun getProduct(id: String, knitterId: String): Product
        fun getProductsByKnitterId(knitterId: String, paging: Paging, sort: Sort): List<Product>
    }

    interface DraftProductRepository {
        fun getDraftProduct(id: String, knitterId: String): DraftProduct
        fun delete(draftProduct: DraftProduct): String
    }
}
