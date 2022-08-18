package com.knitting.kneedle.core.domain.value.unit

data class Length(val value: Double, val unit: Unit = Unit.Cm) {
    init {
        require(value > 0)
    }

    enum class Unit(val code: Int) {
        Cm(1),
    }
}
