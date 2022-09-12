package com.knitting.kneedle.core.controller.designer

import com.knitting.kneedle.core.controller.designer.dto.RegisterDesigner
import com.knitting.kneedle.core.controller.designer.mapper.DesignerRequestMapper
import com.knitting.kneedle.core.controller.designer.mapper.DesignerResponseMapper
import com.knitting.kneedle.core.controller.helper.auth.Authenticated
import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedUser
import com.knitting.kneedle.core.controller.helper.response.ResponseHelper.withJsonResponse
import com.knitting.kneedle.core.usecase.designer.DesignerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("designers")
class DesignerController(
    private val designerService: DesignerService,
) {
    @PostMapping
    suspend fun registerNewDesigner(
        @Authenticated user: AuthenticatedUser,
        @RequestBody body: RegisterDesigner.Request,
    ) = withJsonResponse {
        val request = DesignerRequestMapper.toRegisterData(user.knitterId, body)
        val result = designerService.registerDesigner(request)
        DesignerResponseMapper.toResponse(result)
    }
}
