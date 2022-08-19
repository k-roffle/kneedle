package com.knitting.kneedle.core.infra.repository.design

import com.knitting.kneedle.core.infra.document.DesignDocument
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface DesignMongoRepository : MongoRepository<DesignDocument, String> {
    fun findByIdAndKnitterId(id: String, knitterId: String): DesignDocument?
    fun findAllByKnitterId(knitterId: String, pageable: Pageable): List<DesignDocument>
    fun findAllByKnitterIdAndIdBefore(knitterId: String, id: String, pageable: Pageable): List<DesignDocument>
    fun countByKnitterId(knitterId: String): Int
}
