package com.knitting.kneedle.core.controller.product.mapper

import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.common.pagination.Sort
import com.knitting.kneedle.common.pagination.SortDirection
import com.knitting.kneedle.core.controller.product.dto.CreateProduct
import com.knitting.kneedle.core.controller.product.dto.UpdateProduct
import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.ProductTag
import com.knitting.kneedle.core.domain.value.item.ProductItem
import com.knitting.kneedle.core.usecase.product.dto.CreateProductData
import com.knitting.kneedle.core.usecase.product.dto.GetMyProductData
import com.knitting.kneedle.core.usecase.product.dto.GetMyProductsData
import com.knitting.kneedle.core.usecase.product.dto.UpdateProductData

object ProductRequestMapper {
    fun toCreateProductData(data: CreateProduct.Request, knitterId: String): CreateProductData =
        with(data) {
            CreateProductData(
                knitterId = knitterId,
                name = name,
                fullPrice = Money(fullPrice),
                discountPrice = Money(discountPrice),
                representativeImageUrl = representativeImageUrl,
                specifiedSalesStartedAt = specifiedSalesStartedAt,
                specifiedSalesEndedAt = specifiedSalesEndedAt,
                content = content,
                tags = tags.map { ProductTag(it) },
                items = designIds.map { ProductItem.create(it, ProductItem.Type.DESIGN) },
                draftId = draftId,
            )
        }

    fun toUpdateProductData(data: UpdateProduct.Request, productId: String, knitterId: String): UpdateProductData =
        with(data) {
            UpdateProductData(
                id = productId,
                knitterId = knitterId,
                discountPrice = Money(discountPrice),
                specifiedSalesStartedAt = specifiedSalesStartedAt,
                specifiedSalesEndedAt = specifiedSalesEndedAt,
                content = content,
                tags = tags.map { ProductTag(it) },
                draftId = draftId,
            )
        }

    fun toGetMyProductData(productId: String, knitterId: String) =
        GetMyProductData(
            id = productId,
            knitterId = knitterId,
        )

    fun toGetMyProductsData(paging: Paging, knitterId: String) =
        GetMyProductsData(
            knitterId = knitterId,
            paging = paging,
            sort = Sort("id", SortDirection.DESC),
        )
}
