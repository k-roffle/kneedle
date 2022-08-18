package com.knitting.kneedle.core.infra.document

import com.knitting.kneedle.core.domain.entity.DraftDesign
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.OffsetDateTime

@Document(collection = "draft_design")
class DraftDesignDocument(
    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    val id: String,
    private val knitterId: String,
    private val designId: String?,
    private val value: String,
    private val createdAt: OffsetDateTime = OffsetDateTime.now(),
    private val updatedAt: OffsetDateTime = OffsetDateTime.now(),
) {
    fun toDraftDesign(): DraftDesign =
        DraftDesign(
            id = this.id,
            knitterId = this.knitterId,
            designId = this.designId,
            value = this.value,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
        )
}

fun DraftDesign.toDraftDesignEntity(): DraftDesignDocument =
    DraftDesignDocument(
        id = this.id.ifBlank { ObjectId().toHexString() },
        knitterId = this.knitterId,
        designId = this.designId,
        value = this.value,
        createdAt = this.createdAt ?: OffsetDateTime.now(),
        updatedAt = this.updatedAt ?: OffsetDateTime.now(),
    )
