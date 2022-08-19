package com.knitting.kneedle.core.controller.product.mapper

import com.knitting.kneedle.core.controller.product.dto.CreateProduct
import com.knitting.kneedle.core.controller.product.dto.GetMyProduct
import com.knitting.kneedle.core.controller.product.dto.GetMyProducts
import com.knitting.kneedle.core.controller.product.dto.UpdateProduct
import com.knitting.kneedle.core.domain.entity.Product

object ProductResponseMapper {
    fun toCreateProductResponse(product: Product): CreateProduct.Response =
        with(product) {
            CreateProduct.Response(
                id = id,
            )
        }

    fun toUpdateProductResponse(product: Product): UpdateProduct.Response =
        with(product) {
            UpdateProduct.Response(
                id = id,
            )
        }

    fun toGetMyProductResponse(product: Product): GetMyProduct.Response =
        with(product) {
            GetMyProduct.Response(
                id = id,
                name = name,
                fullPrice = fullPrice.value,
                discountPrice = discountPrice.value,
                representativeImageUrl = representativeImageUrl,
                specifiedSalesStartedAt = specifiedSalesStartedAt,
                specifiedSalesEndedAt = specifiedSalesEndedAt,
                tags = tags.map { it.name },
                content = content,
                inputStatus = "inputStatus",
                itemIds = items.map { it.itemId },
                createdAt = createdAt!!,
                updatedAt = updatedAt!!,
            )
        }

    fun toGetMyProductsResponse(product: Product): GetMyProducts.Response =
        with(product) {
            GetMyProducts.Response(
                id = id,
                name = name,
                fullPrice = fullPrice.value,
                discountPrice = discountPrice.value,
                representativeImageUrl = representativeImageUrl,
                specifiedSalesStartedAt = specifiedSalesStartedAt,
                specifiedSalesEndedAt = specifiedSalesEndedAt,
                tags = tags.map { it.name },
                inputStatus = "inputStatus",
                updatedAt = updatedAt,
            )
        }
}
