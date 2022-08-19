package com.knitting.kneedle.core.controller.product.mapper

import com.knitting.kneedle.core.controller.product.dto.DeleteDraftProduct
import com.knitting.kneedle.core.controller.product.dto.GetMyDraftProduct
import com.knitting.kneedle.core.controller.product.dto.GetMyDraftProductToUpdate
import com.knitting.kneedle.core.controller.product.dto.GetMyDraftProducts
import com.knitting.kneedle.core.controller.product.dto.SaveDraftProduct
import com.knitting.kneedle.core.domain.entity.DraftProduct

object DraftProductResponseMapper {
    fun toSaveDraftProductResponse(draftProduct: DraftProduct): SaveDraftProduct.Response =
        with(draftProduct) {
            SaveDraftProduct.Response(id = id)
        }

    fun toGetMyDraftProductsResponse(draftProduct: DraftProduct): GetMyDraftProducts.Response =
        with(draftProduct) {
            GetMyDraftProducts.Response(
                id = id,
                name = name,
                updatedAt = updatedAt!!,
            )
        }

    fun toGetMyDraftProductResponse(draftProduct: DraftProduct): GetMyDraftProduct.Response =
        with(draftProduct) {
            GetMyDraftProduct.Response(
                id = id,
                value = value,
                updatedAt = updatedAt!!,
            )
        }

    fun toGetMyDraftProductToUpdateResponse(draftProduct: DraftProduct): GetMyDraftProductToUpdate.Response =
        with(draftProduct) {
            GetMyDraftProductToUpdate.Response(
                id = id,
                value = value,
                updatedAt = updatedAt!!,
            )
        }

    fun toDeleteDraftProductResponse(deletedId: String): DeleteDraftProduct.Response =
        DeleteDraftProduct.Response(id = deletedId)
}
