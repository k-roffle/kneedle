package com.knitting.kneedle.core.infra.repository.designer

import com.knitting.kneedle.core.infra.document.DesignerDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface DesignerMongoRepository : MongoRepository<DesignerDocument, String> {
    fun findByKnitterId(knitterId: String): DesignerDocument?
}
