package com.knitting.kneedle.core.usecase.designer.dto

import com.knitting.kneedle.core.domain.value.Account
import com.knitting.kneedle.core.domain.value.Terms

data class RegisterData(
    val knitterId: String,
    val nickname: String,
    val selfIntroduction: String,
    val account: Account,
    val approvedTerms: List<Terms>,
)
