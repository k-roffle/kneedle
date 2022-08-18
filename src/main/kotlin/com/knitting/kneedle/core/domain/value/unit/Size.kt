package com.knitting.kneedle.core.domain.value.unit

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Size(
    val totalLength: Length,
    val sleeveLength: Length,
    val shoulderWidth: Length,
    val bottomWidth: Length,
    val armholeDepth: Length,
)
