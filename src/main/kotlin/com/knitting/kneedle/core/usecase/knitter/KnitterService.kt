package com.knitting.kneedle.core.usecase.knitter

import com.knitting.kneedle.core.domain.entity.Knitter
import org.springframework.stereotype.Service

@Service
class KnitterService(private val knitterRepository: KnitterRepository) {
    fun getKnitter(knitterId: String): Knitter =
        knitterRepository.getById(knitterId)

    interface KnitterRepository {
        fun getById(id: String): Knitter
    }
}
