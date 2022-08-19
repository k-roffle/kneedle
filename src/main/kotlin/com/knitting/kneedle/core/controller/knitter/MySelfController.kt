package com.knitting.kneedle.core.controller.knitter

import com.knitting.kneedle.core.controller.helper.auth.Authenticated
import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedUser
import com.knitting.kneedle.core.controller.helper.response.ResponseHelper.withJsonResponse
import com.knitting.kneedle.core.controller.knitter.mapper.MyselfResponseMapper
import com.knitting.kneedle.core.usecase.knitter.KnitterService
import com.knitting.kneedle.core.usecase.summary.ProductSummaryService
import com.knitting.kneedle.core.usecase.summary.ProfileSummaryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("me")
class MySelfController(
    private val knitterService: KnitterService,
    private val productSummaryService: ProductSummaryService,
    private val profileSummaryService: ProfileSummaryService,
) {
    @GetMapping("profile")
    suspend fun getMyProfile(
        @Authenticated user: AuthenticatedUser
    ) = withJsonResponse {
        val result = knitterService.getKnitter(user.knitterId)
        MyselfResponseMapper.toMyProfileResponse(result)
    }

    @GetMapping("profile/summary")
    suspend fun getMyProfileSummary(
        @Authenticated user: AuthenticatedUser
    ) = withJsonResponse {
        val result = profileSummaryService.getProfileSummary(user.knitterId)
        MyselfResponseMapper.toMyProfileSummaryResponse(result)
    }

    @GetMapping("sales-summary")
    suspend fun getMySalesSummary(
        @Authenticated user: AuthenticatedUser
    ) = withJsonResponse {
        val result = productSummaryService.countProductOnList(user.knitterId)
        MyselfResponseMapper.toSalesSummaryResponse(result)
    }
}
