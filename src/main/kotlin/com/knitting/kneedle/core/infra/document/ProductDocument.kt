package com.knitting.kneedle.core.infra.document

import com.knitting.kneedle.core.domain.entity.Product
import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.ProductTag
import com.knitting.kneedle.core.domain.value.item.ProductItem
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.OffsetDateTime

@Document(collection = "product")
class ProductDocument(
    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    val id: String,
    private val knitterId: String,
    private val name: String,
    private val fullPrice: Money,
    private val discountPrice: Money,
    private val representativeImageUrl: String,
    private val specifiedSalesStartedAt: OffsetDateTime?,
    private val specifiedSalesEndedAt: OffsetDateTime?,
    private val content: String,
    private val tags: List<ProductTag>,
    private val items: List<ProductItem>,
    private val createdAt: OffsetDateTime?,
    private val updatedAt: OffsetDateTime?,
) {
    fun toProduct(): Product =
        Product(
            id = id,
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
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
}

fun Product.toProductEntity() =
    ProductDocument(
        id = id.ifBlank { ObjectId().toHexString() },
        knitterId = knitterId,
        name = name,
        fullPrice = fullPrice,
        discountPrice = discountPrice,
        representativeImageUrl = representativeImageUrl,
        specifiedSalesStartedAt = specifiedSalesStartedAt,
        specifiedSalesEndedAt = specifiedSalesEndedAt,
        content = content,
        items = items,
        tags = tags,
        createdAt = this.createdAt ?: OffsetDateTime.now(),
        updatedAt = this.updatedAt ?: OffsetDateTime.now(),
    )
