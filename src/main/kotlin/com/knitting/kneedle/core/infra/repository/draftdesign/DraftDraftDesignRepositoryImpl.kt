package com.knitting.kneedle.core.infra.repository.draftdesign

import com.knitting.kneedle.core.domain.entity.DraftDesign
import com.knitting.kneedle.core.infra.document.DraftDesignDocument
import com.knitting.kneedle.core.infra.document.toDraftDesignEntity
import com.knitting.kneedle.core.infra.repository.exception.NotFoundEntity
import org.springframework.stereotype.Repository

@Repository
class DraftDraftDesignRepositoryImpl(
    private val draftDesignRepository: DraftDesignMongoRepository,
) : DraftDesignRepository {
    override fun getDraftDesign(id: String, knitterId: String): DraftDesign =
        draftDesignRepository
            .findByIdAndKnitterId(id, knitterId)
            ?.toDraftDesign()
            ?: throw NotFoundEntity(DraftDesignDocument::class.java)

    override fun delete(draftDesign: DraftDesign): String {
        draftDesignRepository.delete(draftDesign.toDraftDesignEntity())
        return draftDesign.id
    }

    override fun findDraftDesignsToCreate(knitterId: String): List<DraftDesign> =
        draftDesignRepository
            .findAllByKnitterIdAndDesignId(knitterId, null)
            .map(DraftDesignDocument::toDraftDesign)

    override fun getDraftDesignToUpdate(designId: String, knitterId: String): DraftDesign =
        draftDesignRepository
            .findByKnitterIdAndDesignId(knitterId = knitterId, designId = designId)
            ?.toDraftDesign()
            ?: throw NotFoundEntity(DraftDesignDocument::class.java)

    private fun save(draftDesign: DraftDesign): DraftDesign =
        draftDesignRepository
            .save(draftDesign.toDraftDesignEntity())
            .toDraftDesign()

    override fun update(draftDesign: DraftDesign): DraftDesign = save(draftDesign)

    override fun create(draftDesign: DraftDesign): DraftDesign = save(draftDesign)
}
