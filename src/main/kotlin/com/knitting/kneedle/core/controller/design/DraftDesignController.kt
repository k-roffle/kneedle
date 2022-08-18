package com.knitting.kneedle.core.controller.design

import com.knitting.kneedle.core.controller.design.dto.SaveDraftDesign
import com.knitting.kneedle.core.controller.design.mapper.DraftDesignRequestMapper
import com.knitting.kneedle.core.controller.design.mapper.DraftDesignResponseMapper
import com.knitting.kneedle.core.controller.helper.auth.Authenticated
import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedUser
import com.knitting.kneedle.core.controller.helper.response.ResponseHelper.withJsonResponse
import com.knitting.kneedle.core.usecase.design.DraftDesignService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("designs/draft")
class DraftDesignController(private val draftDesignService: DraftDesignService) {
    @PostMapping
    suspend fun saveDraft(
        @Authenticated user: AuthenticatedUser,
        @RequestBody body: SaveDraftDesign.Request,
    ) = withJsonResponse {
        val request = DraftDesignRequestMapper.toSaveDraftDesignData(body, user.knitterId)
        val result = draftDesignService.saveDraft(request)
        DraftDesignResponseMapper.toSaveDraftDesignResponse(result)
    }

    @GetMapping("mine")
    suspend fun getMyDraftDesigns(
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val draftDesigns = draftDesignService.getMyDraftDesigns(user.knitterId)
        draftDesigns.map {
            DraftDesignResponseMapper.toGetMyDraftDesignsResponse(it)
        }
    }

    @GetMapping("mine/{draftDesignId}")
    suspend fun getMyDraftDesign(
        @Authenticated user: AuthenticatedUser,
        @PathVariable draftDesignId: String,
    ) = withJsonResponse {
        val draftDesign = draftDesignService.getMyDraftDesign(draftDesignId, user.knitterId)
        DraftDesignResponseMapper.toGetMyDraftDesignResponse(draftDesign)
    }

    @DeleteMapping("mine/{draftDesignId}")
    suspend fun deleteMyDraftDesign(
        @Authenticated user: AuthenticatedUser,
        @PathVariable draftDesignId: String,
    ) = withJsonResponse {
        val result = draftDesignService.deleteMyDraftDesign(draftDesignId, user.knitterId)
        DraftDesignResponseMapper.toDeleteDraftDesignResponse(result)
    }
}
