package com.knitting.kneedle.core.controller.design.mapper

import com.knitting.kneedle.core.controller.design.dto.DeleteDraftDesign
import com.knitting.kneedle.core.controller.design.dto.GetMyDraftDesign
import com.knitting.kneedle.core.controller.design.dto.GetMyDraftDesignToUpdate
import com.knitting.kneedle.core.controller.design.dto.GetMyDraftDesigns
import com.knitting.kneedle.core.controller.design.dto.SaveDraftDesign
import com.knitting.kneedle.core.domain.entity.DraftDesign

object DraftDesignResponseMapper {
    fun toSaveDraftDesignResponse(draftDesign: DraftDesign): SaveDraftDesign.Response =
        with(draftDesign) {
            SaveDraftDesign.Response(id = id)
        }

    fun toGetMyDraftDesignsResponse(draftDesign: DraftDesign): GetMyDraftDesigns.Response =
        with(draftDesign) {
            GetMyDraftDesigns.Response(
                id = id,
                name = name,
                updatedAt = updatedAt!!,
            )
        }

    fun toGetMyDraftDesignResponse(draftDesign: DraftDesign): GetMyDraftDesign.Response =
        with(draftDesign) {
            GetMyDraftDesign.Response(
                id = id,
                value = value,
                updatedAt = updatedAt!!,
            )
        }

    fun toGetMyDraftDesignToUpdateResponse(draftDesign: DraftDesign): GetMyDraftDesignToUpdate.Response =
        with(draftDesign) {
            GetMyDraftDesignToUpdate.Response(
                id = id,
                value = value,
                updatedAt = updatedAt!!,
            )
        }

    fun toDeleteDraftDesignResponse(deletedId: String): DeleteDraftDesign.Response =
        DeleteDraftDesign.Response(id = deletedId)
}
