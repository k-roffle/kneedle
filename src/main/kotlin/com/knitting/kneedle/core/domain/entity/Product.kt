package com.knitting.kneedle.core.domain.entity

import com.knitting.kneedle.core.domain.exception.product.InvalidDiscountPrice
import com.knitting.kneedle.core.domain.exception.product.InvalidFullPrice
import com.knitting.kneedle.core.domain.exception.product.InvalidPeriod
import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.ProductTag
import com.knitting.kneedle.core.domain.value.item.ProductItem
import java.time.OffsetDateTime

data class Product(
    val id: String,
    val knitterId: String,
    val name: String,
    val fullPrice: Money,
    val discountPrice: Money,
    val representativeImageUrl: String,
    val specifiedSalesStartedAt: OffsetDateTime?,
    val specifiedSalesEndedAt: OffsetDateTime?,
    val content: String,
    val tags: List<ProductTag>,
    val items: List<ProductItem>,
    val createdAt: OffsetDateTime?,
    val updatedAt: OffsetDateTime?,
) {
    init {
        require(
            specifiedSalesStartedAt == null ||
                specifiedSalesEndedAt == null ||
                specifiedSalesStartedAt <= specifiedSalesEndedAt
        ) {
            throw InvalidPeriod()
        }

        require(
            Money.ZERO < discountPrice &&
                discountPrice <= fullPrice
        ) {
            throw InvalidDiscountPrice()
        }

        require(fullPrice > Money.ZERO) {
            throw InvalidFullPrice()
        }
    }

    private val salesStatus: SalesStatus
        get() {
            val today = OffsetDateTime.now()
            val start = specifiedSalesStartedAt ?: OffsetDateTime.MIN
            val end = specifiedSalesEndedAt ?: OffsetDateTime.MAX
            return if (start > today) {
                SalesStatus.RESERVED
            } else if (end < today) {
                SalesStatus.COMPLETED
            } else {
                SalesStatus.ON_SALES
            }
        }

    val onList: Boolean
        get() = salesStatus == SalesStatus.ON_SALES

    fun update(
        discountPrice: Money,
        specifiedSalesStartedAt: OffsetDateTime?,
        specifiedSalesEndedAt: OffsetDateTime?,
        content: String,
        tags: List<ProductTag>,
    ) = this.copy(
        discountPrice = discountPrice,
        specifiedSalesStartedAt = specifiedSalesStartedAt,
        specifiedSalesEndedAt = specifiedSalesEndedAt,
        content = content,
        tags = tags,
        updatedAt = OffsetDateTime.now(),
    )

    enum class SalesStatus {
        RESERVED,
        ON_SALES,
        COMPLETED,
    }

    companion object {
        fun new(
            knitterId: String,
            name: String,
            fullPrice: Money,
            discountPrice: Money,
            representativeImageUrl: String,
            specifiedSalesStartedAt: OffsetDateTime?,
            specifiedSalesEndedAt: OffsetDateTime?,
            content: String,
            tags: List<ProductTag>,
            items: List<ProductItem>,
        ): Product {
            return Product(
                id = "",
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
                null,
                null,
            )
        }
    }
}
