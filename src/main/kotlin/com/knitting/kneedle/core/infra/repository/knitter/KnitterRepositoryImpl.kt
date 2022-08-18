package com.knitting.kneedle.core.infra.repository.knitter

import com.knitting.kneedle.core.domain.entity.Knitter
import com.knitting.kneedle.core.infra.document.toKnitterEntity
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
}
