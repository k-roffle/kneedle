package com.knitting.kneedle.core.controller.product.mapper

import com.knitting.kneedle.core.controller.product.dto.SaveDraftProduct
import com.knitting.kneedle.core.usecase.product.dto.SaveDraftProductData

object DraftProductRequestMapper {
    fun toSaveDraftProductData(
        data: SaveDraftProduct.Request,
        knitterId: String,
    ): SaveDraftProductData =
        with(data) {
            SaveDraftProductData(
                id = id,
                knitterId = knitterId,
                productId = productId,
                value = value,
            )
        }
}
