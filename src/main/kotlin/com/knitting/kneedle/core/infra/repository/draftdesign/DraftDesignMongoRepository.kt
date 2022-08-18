package com.knitting.kneedle.core.infra.repository.draftdesign

import com.knitting.kneedle.core.infra.document.DraftDesignDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface DraftDesignMongoRepository : MongoRepository<DraftDesignDocument, String> {
    fun findByIdAndKnitterId(id: String, knitterId: String): DraftDesignDocument?
    fun findAllByKnitterIdAndDesignId(knitterId: String, designId: String?): List<DraftDesignDocument>
    fun findByKnitterIdAndDesignId(knitterId: String, designId: String): DraftDesignDocument?
}
