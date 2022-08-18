package com.knitting.kneedle.core.usecase.design

import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.common.pagination.Sort
import com.knitting.kneedle.core.domain.entity.Design
import com.knitting.kneedle.core.domain.entity.DraftDesign
import com.knitting.kneedle.core.usecase.design.dto.CreateDesignData
import com.knitting.kneedle.core.usecase.design.dto.GetMyDesignData
import com.knitting.kneedle.core.usecase.design.dto.MyDesignFilter
import com.knitting.kneedle.core.usecase.design.dto.UpdateDesignData
import org.springframework.stereotype.Service

@Service
class DesignService(
    private val designRepository: DesignRepository,
    private val draftDesignRepository: DraftDesignRepository,
) {
    private fun deleteMyDraftDesign(draftId: String?, knitterId: String) {
        draftId ?: return
        val draftDesign = draftDesignRepository.getDraftDesign(id = draftId, knitterId = knitterId)
        draftDesignRepository.delete(draftDesign)
    }

    suspend fun create(data: CreateDesignData): Design {
        val design = with(data) {
            Design.new(
                knitterId = knitterId,
                name = name,
                designType = designType,
                patternType = patternType,
                gauge = gauge,
                size = size,
                needle = needle,
                yarn = yarn,
                extra = extra,
                price = price,
                pattern = pattern,
                description = description,
                targetLevel = targetLevel,
                coverImageUrl = coverImageUrl,
                techniques = techniques,
            )
        }
        val createdDesign = designRepository.createDesign(design)
        deleteMyDraftDesign(data.draftId, data.knitterId)
        return createdDesign
    }

    suspend fun update(data: UpdateDesignData): Design {
        val design = with(data) {
            designRepository
                .getDesign(data.id, data.knitterId)
                .update(
                    designType = designType,
                    patternType = patternType,
                    gauge = gauge,
                    size = size,
                    needle = needle,
                    yarn = yarn,
                    extra = extra,
                    pattern = pattern,
                    description = description,
                    targetLevel = targetLevel,
                    techniques = techniques,
                )
        }
        val updatedDesign = designRepository.updateDesign(design)
        deleteMyDraftDesign(data.draftId, data.knitterId)
        return updatedDesign
    }

    suspend fun getMyDesign(data: GetMyDesignData): Design =
        with(data) {
            designRepository.getDesign(id, knitterId)
        }

    suspend fun getMyDesigns(filter: MyDesignFilter): List<Design> =
        with(filter) {
            designRepository.getDesignsByKnitterId(knitterId, paging, sort)
        }

    interface DesignRepository {
        fun getDesign(id: String, knitterId: String): Design
        fun createDesign(design: Design): Design
        fun updateDesign(design: Design): Design
        fun getDesignsByKnitterId(knitterId: String, paging: Paging, sort: Sort): List<Design>
    }

    interface DraftDesignRepository {
        fun getDraftDesign(id: String, knitterId: String): DraftDesign
        fun delete(draftDesign: DraftDesign): String
    }
}
