package com.knitting.kneedle.core.controller.design.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload
import com.knitting.kneedle.core.domain.entity.Design

object NewDesign {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(
        val name: String,
        val coverImageUrl: String,
        val description: String?,
        val price: Int,
        val designType: Design.DesignType,
        val patternType: Design.PatternType,
        val stitches: Double,
        val rows: Double,
        val needle: String,
        val techniques: List<String>?,
        val targetLevel: Design.LevelType?,
        val yarn: String?,
        val extra: String?,
        val size: SizeDto?,
        val pattern: String,
        val draftId: String?,
    )

    data class Response(
        val id: String,
    ) : ObjectPayload
}
