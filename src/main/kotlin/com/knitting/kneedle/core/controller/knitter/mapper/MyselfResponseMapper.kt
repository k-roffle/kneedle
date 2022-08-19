package com.knitting.kneedle.core.controller.knitter.mapper

import com.knitting.kneedle.core.controller.knitter.dto.MyProfile
import com.knitting.kneedle.core.controller.knitter.dto.MyProfileSummary
import com.knitting.kneedle.core.controller.knitter.dto.SalesSummary
import com.knitting.kneedle.core.domain.entity.Knitter
import com.knitting.kneedle.core.usecase.summary.dto.ProfileSummary

object MyselfResponseMapper {
    fun toMyProfileResponse(knitter: Knitter): MyProfile.Response =
        with(knitter) {
            MyProfile.Response(
                email = email,
                name = name,
                profileImageUrl = profileImageUrl,
            )
        }

    fun toSalesSummaryResponse(numberOfProductsOnSales: Int): SalesSummary.Response =
        SalesSummary.Response(
            numberOfProductsOnSales = numberOfProductsOnSales,
            numberOfProductsSold = 0,
        )

    fun toMyProfileSummaryResponse(summary: ProfileSummary): MyProfileSummary.Response =
        with(summary) {
            MyProfileSummary.Response(
                myDesignsCount = myDesignsCount,
                myProductsCount = myProductsCount,
                purchasedProductsCount = purchasedProductsCount,
            )
        }
}
