package com.knitting.kneedle.core.controller.designer.mapper

import com.knitting.kneedle.core.controller.designer.dto.RegisterDesigner
import com.knitting.kneedle.core.domain.value.Account
import com.knitting.kneedle.core.domain.value.Terms
import com.knitting.kneedle.core.usecase.designer.dto.RegisterData

object DesignerRequestMapper {
    fun toRegisterData(knitterId: String, request: RegisterDesigner.Request) =
        with(request) {
            RegisterData(
                knitterId = knitterId,
                nickname = nickname,
                selfIntroduction = selfIntroduction,
                account = Account(
                    bank = Account.Bank.valueOf(account.bank),
                    number = account.number,
                    holderName = account.holderName,
                ),
                approvedTerms = approvedTermsKeys.map { Terms.findByKey(it) },
            )
        }
}
