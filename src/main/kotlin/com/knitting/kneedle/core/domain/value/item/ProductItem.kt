package com.knitting.kneedle.core.domain.value.item

abstract class ProductItem(val itemId: String) {
    abstract val type: Type

    enum class Type {
        GOODS,
        DESIGN
    }

    companion object {
        fun create(itemId: String, type: Type): ProductItem {
            return when (type) {
                Type.DESIGN -> DesignProductItem(itemId)
                Type.GOODS -> GoodsProductItem(itemId)
            }
        }
    }
}
