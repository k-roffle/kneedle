package com.knitting.kneedle.core.controller.design.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload
import com.knitting.kneedle.core.domain.entity.Design
import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.Pattern
import com.knitting.kneedle.core.domain.value.Technique
import com.knitting.kneedle.core.domain.value.unit.Gauge
import java.time.OffsetDateTime

object MyDesign {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Response(
        val id: String,
        val name: String,
        val designType: Design.DesignType,
        val patternType: Design.PatternType,
        val gauge: Gauge,
        val size: SizeDto?,
        val needle: String,
        val yarn: String?,
        val extra: String?,
        val price: Money,
        val pattern: Pattern,
        val description: String?,
        val targetLevel: Design.LevelType?,
        val coverImageUrl: String,
        val techniques: List<Technique>?,
        val updatedAt: OffsetDateTime?,
        val createdAt: OffsetDateTime?,
    ) : ObjectPayload
}
