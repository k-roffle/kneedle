package com.knitting.kneedle.core.controller.designer.mapper

import com.knitting.kneedle.core.controller.designer.dto.RegisterDesigner
import com.knitting.kneedle.core.domain.entity.Designer

object DesignerResponseMapper {
    fun toResponse(designer: Designer) =
        with(designer) {
            RegisterDesigner.Response(id = id)
        }
}
