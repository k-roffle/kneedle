package com.knitting.kneedle.core.controller.design.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.knitting.kneedle.core.domain.value.unit.Length

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class SizeDto(
    val sizeUnit: Length.Unit = Length.Unit.Cm,
    val totalLength: Double,
    val sleeveLength: Double,
    val shoulderWidth: Double,
    val bottomWidth: Double,
    val armholeDepth: Double,
)
