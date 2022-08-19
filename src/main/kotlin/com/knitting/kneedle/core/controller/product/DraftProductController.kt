package com.knitting.kneedle.core.controller.product

import com.knitting.kneedle.core.controller.helper.auth.Authenticated
import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedUser
import com.knitting.kneedle.core.controller.helper.response.ResponseHelper.withJsonResponse
import com.knitting.kneedle.core.controller.product.dto.SaveDraftProduct
import com.knitting.kneedle.core.controller.product.mapper.DraftProductRequestMapper
import com.knitting.kneedle.core.controller.product.mapper.DraftProductResponseMapper
import com.knitting.kneedle.core.usecase.product.DraftProductService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products/draft")
class DraftProductController(
    private val draftProductService: DraftProductService,
) {
    @GetMapping("mine")
    suspend fun getMyDraftProducts(
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        draftProductService
            .getMyDraftProducts(user.knitterId)
            .map(DraftProductResponseMapper::toGetMyDraftProductsResponse)
    }

    @GetMapping("mine/{draftProductId}")
    suspend fun getMyDraftProduct(
        @PathVariable draftProductId: String,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val result = draftProductService.getMyDraftProduct(draftProductId, user.knitterId)
        DraftProductResponseMapper.toGetMyDraftProductResponse(result)
    }

    @PostMapping
    suspend fun saveDraft(
        @RequestBody body: SaveDraftProduct.Request,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val request = DraftProductRequestMapper.toSaveDraftProductData(body, user.knitterId)
        val result = draftProductService.saveDraft(request)
        DraftProductResponseMapper.toSaveDraftProductResponse(result)
    }

    @DeleteMapping("mine/{draftProductId}")
    suspend fun deleteMyDraftProduct(
        @PathVariable draftProductId: String,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val result = draftProductService.deleteMyDraftProduct(draftProductId, user.knitterId)
        DraftProductResponseMapper.toDeleteDraftProductResponse(result)
    }
}
