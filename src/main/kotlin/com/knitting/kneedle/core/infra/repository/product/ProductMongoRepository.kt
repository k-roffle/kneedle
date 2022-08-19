package com.knitting.kneedle.core.infra.repository.product

import com.knitting.kneedle.core.infra.document.ProductDocument
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductMongoRepository : MongoRepository<ProductDocument, String> {
    fun findByIdAndKnitterId(id: String, knitterId: String): ProductDocument?
    fun findAllByKnitterId(knitterId: String, pageable: Pageable): List<ProductDocument>
    fun findAllByKnitterIdAndIdBefore(knitterId: String, id: String, pageable: Pageable): List<ProductDocument>
    fun countByKnitterId(knitterId: String): Int
}
