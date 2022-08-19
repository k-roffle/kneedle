package com.knitting.kneedle.core.infra.repository.knitter

import com.knitting.kneedle.core.domain.entity.Knitter
import com.knitting.kneedle.core.infra.document.KnitterDocument
import com.knitting.kneedle.core.infra.document.toKnitterEntity
import com.knitting.kneedle.core.infra.repository.exception.NotFoundEntity
import org.springframework.stereotype.Repository

@Repository
class KnitterRepositoryImpl(
    private val knitterRepository: KnitterMongoRepository,
) : KnitterRepository {
    override fun create(user: Knitter): Knitter =
        knitterRepository
            .save(user.toKnitterEntity())
            .toKnitter()

    override fun findByEmail(email: String): Knitter? =
        knitterRepository
            .findFirstByEmail(email)
            ?.toKnitter()

    override fun getById(id: String): Knitter {
        val knitter = knitterRepository.findById(id)
        if (knitter.isEmpty)
            throw NotFoundEntity(KnitterDocument::class.java)
        return knitter.get().toKnitter()
    }
}
