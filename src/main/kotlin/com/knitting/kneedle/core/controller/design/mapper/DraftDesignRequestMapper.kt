package com.knitting.kneedle.core.controller.design.mapper

import com.knitting.kneedle.core.controller.design.dto.SaveDraftDesign
import com.knitting.kneedle.core.usecase.design.dto.SaveDraftDesignData

object DraftDesignRequestMapper {
    fun toSaveDraftDesignData(
        data: SaveDraftDesign.Request,
        knitterId: String,
    ): SaveDraftDesignData =
        with(data) {
            SaveDraftDesignData(
                id = id,
                knitterId = knitterId,
                designId = designId,
                value = value,
            )
        }
}
