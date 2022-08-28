package com.knitting.kneedle.core.controller.design

import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.core.controller.design.dto.NewDesign
import com.knitting.kneedle.core.controller.design.dto.UpdateDesign
import com.knitting.kneedle.core.controller.design.mapper.DesignRequestMapper
import com.knitting.kneedle.core.controller.design.mapper.DesignResponseMapper
import com.knitting.kneedle.core.controller.design.mapper.DraftDesignResponseMapper
import com.knitting.kneedle.core.controller.helper.auth.Authenticated
import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedUser
import com.knitting.kneedle.core.controller.helper.response.ResponseHelper.withJsonResponse
import com.knitting.kneedle.core.usecase.design.DesignService
import com.knitting.kneedle.core.usecase.design.DraftDesignService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@RequestMapping("designs")
class DesignController(
    private val designService: DesignService,
    private val draftDesignService: DraftDesignService,
) {
    @PostMapping
    suspend fun createDesign(
        @Authenticated user: AuthenticatedUser,
        @RequestBody body: NewDesign.Request,
    ) = withJsonResponse {
        val request = DesignRequestMapper.toCreateDesignData(body, user.knitterId)
        val result = designService.create(request)
        DesignResponseMapper.toNewDesignResponse(result)
    }

    @PutMapping("{designId}")
    suspend fun updateDesign(
        @PathVariable designId: String,
        @Authenticated user: AuthenticatedUser,
        @RequestBody body: UpdateDesign.Request,
    ) = withJsonResponse {
        val request = DesignRequestMapper.toUpdateDesignData(body, designId, user.knitterId)
        val result = designService.update(request)
        DesignResponseMapper.toUpdateDesignResponse(result)
    }

    @GetMapping("mine/{designId}")
    suspend fun getMyDesign(
        @PathVariable designId: String,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val request = DesignRequestMapper.toGetMyDesignData(designId, user.knitterId)
        val result = designService.getMyDesign(request)
        DesignResponseMapper.toMyDesignResponse(result)
    }

    @GetMapping("mine")
    suspend fun getMyDesigns(
        @RequestParam after: String?,
        @RequestParam @Min(1) @Max(30) count: Int?,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        designService
            .getMyDesigns(DesignRequestMapper.toMyDesignFilter(Paging(after, count), user.knitterId))
            .map(DesignResponseMapper::toMyDesignsResponse)
    }

    @GetMapping("{designId}/draft/mine")
    suspend fun getMyDraftDesignToUpdate(
        @PathVariable designId: String,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val draftDesign = draftDesignService.getMyDraftDesignToUpdate(designId = designId, knitterId = user.knitterId)
        DraftDesignResponseMapper.toGetMyDraftDesignToUpdateResponse(draftDesign)
    }
}
