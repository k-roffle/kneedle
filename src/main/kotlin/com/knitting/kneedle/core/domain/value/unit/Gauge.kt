package com.knitting.kneedle.core.domain.value.unit

data class Gauge(val stitches: Double, val rows: Double) {
    init {
        require(this.stitches > 0 && this.rows > 0)
    }
}
