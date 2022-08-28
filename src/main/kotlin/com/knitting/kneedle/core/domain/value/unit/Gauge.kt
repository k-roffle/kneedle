package com.knitting.kneedle.core.domain.value.unit

import com.knitting.kneedle.core.domain.exception.InvalidGauge

data class Gauge(val stitches: Double, val rows: Double) {
    init {
        require(this.stitches > 0 && this.rows > 0) {
            throw InvalidGauge()
        }
    }
}
