package com.knitting.kneedle.common.extension

import java.time.OffsetDateTime
import java.time.ZoneOffset

fun OffsetDateTime.onUTC() = this.withOffsetSameInstant(ZoneOffset.UTC)
