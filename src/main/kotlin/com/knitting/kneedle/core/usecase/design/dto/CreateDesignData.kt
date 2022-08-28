package com.knitting.kneedle.core.usecase.design.dto

import com.knitting.kneedle.core.domain.entity.Design
import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.Pattern
import com.knitting.kneedle.core.domain.value.Technique
import com.knitting.kneedle.core.domain.value.unit.Gauge
import com.knitting.kneedle.core.domain.value.unit.Size

data class CreateDesignData(
    val knitterId: String,
    val name: String,
    val designType: Design.DesignType,
    val patternType: Design.PatternType,
    val gauge: Gauge,
    val size: Size?,
    val needle: String,
    val yarn: String?,
    val extra: String?,
    val price: Money,
    val pattern: Pattern,
    val description: String?,
    val targetLevel: Design.LevelType?,
    val coverImageUrl: String,
    val techniques: List<Technique>?,
    val draftId: String?,
)
