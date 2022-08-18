package com.knitting.kneedle.core.usecase.design.dto

data class SaveDraftDesignData(
    val id: String?,
    val knitterId: String,
    val designId: String?,
    val value: String,
)
