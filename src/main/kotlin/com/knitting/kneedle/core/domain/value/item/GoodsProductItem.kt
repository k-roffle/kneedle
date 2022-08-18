package com.knitting.kneedle.core.domain.value.item

class GoodsProductItem(itemId: Long) : ProductItem(itemId) {
    override val type: ProductItem.Type
        get() = ProductItem.Type.GOODS
}
