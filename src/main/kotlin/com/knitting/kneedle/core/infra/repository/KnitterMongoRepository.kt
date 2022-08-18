package com.knitting.kneedle.core.infra.repository

import com.knitting.kneedle.core.infra.document.KnitterDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface KnitterMongoRepository : MongoRepository<KnitterDocument, String> {
    fun findFirstByEmail(email: String): KnitterDocument?
}
