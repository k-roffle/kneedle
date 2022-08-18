package com.knitting.kneedle.core.controller.helper.auth

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Authenticated(
    val required: Boolean = true
)
