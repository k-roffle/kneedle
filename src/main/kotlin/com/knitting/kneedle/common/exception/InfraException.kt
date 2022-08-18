package com.knitting.kneedle.common.exception

abstract class InfraException(override val message: String) : BaseException(message = message) {
    override val layer: ExceptionLayer = ExceptionLayer.INFRA
}
