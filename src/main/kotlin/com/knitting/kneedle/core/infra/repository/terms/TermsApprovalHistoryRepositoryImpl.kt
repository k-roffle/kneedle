package com.knitting.kneedle.core.infra.repository.terms

import com.knitting.kneedle.core.domain.entity.TermsApprovalHistory
import com.knitting.kneedle.core.infra.document.toTermsApprovalHistoryEntity
import org.springframework.stereotype.Repository

@Repository
class TermsApprovalHistoryRepositoryImpl(
    private val termsRepository: TermsApprovalHistoryMongoRepository,
) : TermsApprovalHistoryRepository {
    override fun create(termsApprovalHistories: List<TermsApprovalHistory>): List<TermsApprovalHistory> {
        return termsRepository
            .saveAll(termsApprovalHistories.map { it.toTermsApprovalHistoryEntity() })
            .map { it.toTermsApprovalHistory() }
    }
}
