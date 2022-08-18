package com.knitting.kneedle.core.domain.value.item

class DesignProductItem(itemId: Long) : ProductItem(itemId) {
    override val type: Type
        get() = Type.DESIGN
}
