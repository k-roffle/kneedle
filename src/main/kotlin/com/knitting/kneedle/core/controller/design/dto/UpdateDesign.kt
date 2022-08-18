package com.knitting.kneedle.core.controller.design.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.controller.helper.response.type.ObjectPayload
import com.knitting.kneedle.core.domain.entity.Design

object UpdateDesign {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(
        val designType: Design.DesignType,
        val patternType: Design.PatternType,
        val stitches: Double,
        val rows: Double,
        val size: SizeDto,
        val needle: String,
        val yarn: String,
        val extra: String?,
        val pattern: String,
        val description: String,
        val targetLevel: Design.LevelType,
        val techniques: List<String>,
        val draftId: String?,
    )

    data class Response(
        val id: String,
    ) : ObjectPayload
}
