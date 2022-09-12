package com.knitting.kneedle.core.controller.designer.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

object RegisterDesigner {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Request(
        val nickname: String,
        val selfIntroduction: String,
        val account: Account,
        val approvedTermsKeys: List<String>,
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
        data class Account(
            val bank: String,
            val number: String,
            val holderName: String,
        )
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class Response(
        val id: String,
    )
}
