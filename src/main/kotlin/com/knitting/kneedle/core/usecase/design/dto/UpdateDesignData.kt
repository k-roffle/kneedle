package com.knitting.kneedle.core.usecase.design.dto

import com.knitting.kneedle.core.domain.entity.Design
import com.knitting.kneedle.core.domain.value.Pattern
import com.knitting.kneedle.core.domain.value.Technique
import com.knitting.kneedle.core.domain.value.unit.Gauge
import com.knitting.kneedle.core.domain.value.unit.Size

data class UpdateDesignData(
    val id: String,
    val knitterId: String,
    val designType: Design.DesignType,
    val patternType: Design.PatternType,
    val gauge: Gauge,
    val size: Size,
    val needle: String,
    val yarn: String,
    val extra: String?,
    val pattern: Pattern,
    val description: String,
    val targetLevel: Design.LevelType,
    val techniques: List<Technique>,
    val draftId: String?,
)
