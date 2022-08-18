package com.knitting.kneedle.core.infra.document

import com.knitting.kneedle.core.domain.entity.Design
import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.Pattern
import com.knitting.kneedle.core.domain.value.Technique
import com.knitting.kneedle.core.domain.value.unit.Gauge
import com.knitting.kneedle.core.domain.value.unit.Size
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.OffsetDateTime

@Document(collection = "design")
class DesignDocument(
    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    val id: String,
    private val knitterId: String,
    private val name: String,
    private val designType: String,
    private val patternType: String,
    private val gauge: Gauge,
    private val size: Size,
    private val needle: String,
    private val yarn: String,
    private val extra: String?,
    private val price: Money,
    private val pattern: Pattern,
    private val description: String,
    private val targetLevel: String,
    private val coverImageUrl: String,
    private val techniques: List<Technique>,
    private val updatedAt: OffsetDateTime = OffsetDateTime.now(),
    private val createdAt: OffsetDateTime = OffsetDateTime.now(),
) {
    fun toDesign(): Design =
        Design(
            id = this.id,
            knitterId = this.knitterId,
            name = this.name,
            designType = Design.DesignType.valueOf(this.designType),
            patternType = Design.PatternType.valueOf(this.patternType),
            gauge = this.gauge,
            size = this.size,
            needle = this.needle,
            yarn = this.yarn,
            extra = this.extra,
            price = this.price,
            pattern = this.pattern,
            description = this.description,
            targetLevel = Design.LevelType.valueOf(this.targetLevel),
            coverImageUrl = this.coverImageUrl,
            techniques = techniques,
            updatedAt = this.updatedAt,
            createdAt = this.createdAt,
        )
}

fun Design.toDesignEntity() =
    DesignDocument(
        id = this.id.ifBlank { ObjectId().toHexString() },
        knitterId = this.knitterId,
        name = this.name,
        designType = this.designType.name,
        patternType = this.patternType.name,
        gauge = this.gauge,
        needle = this.needle,
        yarn = this.yarn,
        extra = this.extra,
        price = this.price,
        pattern = this.pattern,
        description = this.description,
        targetLevel = this.targetLevel.name,
        coverImageUrl = this.coverImageUrl,
        size = this.size,
        techniques = this.techniques,
        updatedAt = this.updatedAt ?: OffsetDateTime.now(),
        createdAt = this.createdAt ?: OffsetDateTime.now(),
    )
