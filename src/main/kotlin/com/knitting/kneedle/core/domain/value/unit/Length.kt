package com.knitting.kneedle.core.domain.value.unit

import com.knitting.kneedle.core.domain.exception.InvalidLength

data class Length(val value: Double, val unit: Unit = Unit.Cm) {
    init {
        require(value > 0) {
            throw InvalidLength()
        }
    }

    enum class Unit(val code: Int) {
        Cm(1),
    }
}
