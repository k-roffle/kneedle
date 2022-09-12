package com.knitting.kneedle.core.infra.document

import com.knitting.kneedle.core.domain.entity.TermsApprovalHistory
import com.knitting.kneedle.core.domain.value.Terms
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.OffsetDateTime

@Document(collection = "terms_approval_history")
class TermsApprovalHistoryDocument(
    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    val id: String,
    private val knitterId: String,
    private val approvedTermsKey: String,
    private val action: String,
    private val createdAt: OffsetDateTime = OffsetDateTime.now(),
) {
    fun toTermsApprovalHistory(): TermsApprovalHistory =
        TermsApprovalHistory(
            id = this.id,
            knitterId = this.knitterId,
            approvedTermsKey = Terms.findByKey(this.approvedTermsKey),
            action = TermsApprovalHistory.Action.valueOf(this.action),
            createdAt = this.createdAt,
        )
}

fun TermsApprovalHistory.toTermsApprovalHistoryEntity() =
    TermsApprovalHistoryDocument(
        id = this.id.ifBlank { ObjectId().toHexString() },
        knitterId = this.knitterId,
        approvedTermsKey = this.approvedTermsKey.key,
        action = this.action.name,
        createdAt = this.createdAt,
    )
