package com.knitting.kneedle.core.infra.document

import com.knitting.kneedle.core.domain.entity.Knitter
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.OffsetDateTime

@Document(collection = "knitter")
class KnitterDocument(
    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    val id: String,
    private val email: String,
    private val name: String?,
    private val profileImageUrl: String?,
    private val createdAt: OffsetDateTime = OffsetDateTime.now(),
) {
    fun toKnitter(): Knitter =
        Knitter(
            id = this.id,
            email = this.email,
            name = this.name,
            profileImageUrl = this.profileImageUrl,
            createdAt = this.createdAt,
        )
}

fun Knitter.toKnitterEntity() =
    KnitterDocument(
        id = this.id.ifBlank { ObjectId().toHexString() },
        email = this.email,
        name = this.name,
        profileImageUrl = this.profileImageUrl,
        createdAt = this.createdAt ?: OffsetDateTime.now(),
    )
