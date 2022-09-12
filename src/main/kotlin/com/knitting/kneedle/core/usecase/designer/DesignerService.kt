package com.knitting.kneedle.core.usecase.designer

import com.knitting.kneedle.core.domain.entity.Designer
import com.knitting.kneedle.core.domain.entity.Knitter
import com.knitting.kneedle.core.domain.entity.TermsApprovalHistory
import com.knitting.kneedle.core.domain.value.Terms
import com.knitting.kneedle.core.usecase.designer.dto.RegisterData
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DesignerService(
    private val knitterRepository: KnitterRepository,
    private val designerRepository: DesignerRepository,
    private val termsApprovalHistoryRepository: TermsApprovalHistoryRepository,
) {
    @Transactional
    suspend fun registerDesigner(data: RegisterData): Designer {
        validateExistsDesigner(data.knitterId)
        val knitter = knitterRepository.getById(data.knitterId)
        val designer = with(data) {
            Designer.create(
                knitter = knitter,
                nickname = nickname,
                selfIntroduction = selfIntroduction,
                account = account,
            )
        }

        val savedDesigner = designerRepository
            .create(designer)
            .also { saveApprovalTermsHistory(knitterId = it.knitterId, keys = data.approvedTerms) }
        return savedDesigner
    }

    private fun validateExistsDesigner(knitterId: String) {
        designerRepository.findByKnitterId(knitterId) ?: return
        throw IllegalStateException("Knitter(id=$knitterId) has already registered a designer.")
    }

    private fun saveApprovalTermsHistory(knitterId: String, keys: List<Terms>) {
        val approvalTermsHistories = keys.map {
            TermsApprovalHistory.create(
                knitterId = knitterId,
                approvedTermsKey = it,
            )
        }
        termsApprovalHistoryRepository.create(approvalTermsHistories)
    }

    interface KnitterRepository {
        fun getById(id: String): Knitter
    }

    interface DesignerRepository {
        fun create(designer: Designer): Designer
        fun findByKnitterId(knitterId: String): Designer?
    }

    interface TermsApprovalHistoryRepository {
        fun create(termsApprovalHistories: List<TermsApprovalHistory>): List<TermsApprovalHistory>
    }
}
