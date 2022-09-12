package com.knitting.kneedle.core.infra.repository.terms

import com.knitting.kneedle.core.infra.document.TermsApprovalHistoryDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface TermsApprovalHistoryMongoRepository : MongoRepository<TermsApprovalHistoryDocument, String>
