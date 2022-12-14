package com.knitting.kneedle.common.extension

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

fun LocalDateTime.toDate(): Date = Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
