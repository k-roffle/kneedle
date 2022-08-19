package com.knitting.kneedle.core.infra.repository.draftproduct

import com.knitting.kneedle.core.infra.document.DraftProductDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface DraftProductMongoRepository : MongoRepository<DraftProductDocument, String> {
    fun findByIdAndKnitterId(id: String, knitterId: String): DraftProductDocument?
    fun findAllByKnitterIdAndProductId(knitterId: String, productId: String?): List<DraftProductDocument>
    fun findByKnitterIdAndProductId(knitterId: String, productId: String): DraftProductDocument?
}
