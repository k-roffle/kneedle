package com.knitting.kneedle.core.usecase.design

import com.knitting.kneedle.core.domain.entity.Design
import com.knitting.kneedle.core.domain.entity.DraftDesign
import com.knitting.kneedle.core.usecase.design.dto.SaveDraftDesignData
import org.springframework.stereotype.Service

@Service
class DraftDesignService(
    private val draftDesignRepository: DraftDesignRepository,
    private val designRepository: DesignRepository,
) {
    fun saveDraft(data: SaveDraftDesignData): DraftDesign {
        if (data.designId != null) {
            designRepository.getDesign(data.designId, data.knitterId)
        }
        if (data.id == null) {
            return draftDesignRepository.create(
                DraftDesign.new(
                    knitterId = data.knitterId,
                    designId = data.designId,
                    value = data.value,
                )
            )
        }
        val mergedDraftDesign = draftDesignRepository
            .getDraftDesign(data.id, data.knitterId)
            .merge(data.value)
        return draftDesignRepository.update(mergedDraftDesign)
    }

    fun getMyDraftDesigns(knitterId: String): List<DraftDesign> =
        draftDesignRepository.findDraftDesignsToCreate(knitterId)

    fun getMyDraftDesign(draftDesignId: String, knitterId: String): DraftDesign =
        draftDesignRepository.getDraftDesign(
            id = draftDesignId,
            knitterId = knitterId,
        )

    fun getMyDraftDesignToUpdate(designId: String, knitterId: String): DraftDesign =
        draftDesignRepository.getDraftDesignToUpdate(
            designId = designId,
            knitterId = knitterId,
        )

    fun deleteMyDraftDesign(draftDesignId: String, knitterId: String): String {
        val draftDesign = draftDesignRepository.getDraftDesign(draftDesignId, knitterId)
        return draftDesignRepository.delete(draftDesign)
    }

    interface DraftDesignRepository {
        fun getDraftDesign(id: String, knitterId: String): DraftDesign
        fun findDraftDesignsToCreate(knitterId: String): List<DraftDesign>
        fun getDraftDesignToUpdate(designId: String, knitterId: String): DraftDesign
        fun update(draftDesign: DraftDesign): DraftDesign
        fun create(draftDesign: DraftDesign): DraftDesign
        fun delete(draftDesign: DraftDesign): String
    }

    interface DesignRepository {
        fun getDesign(id: String, knitterId: String): Design
    }
}
