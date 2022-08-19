package com.knitting.kneedle.core.infra.repository.product

import com.knitting.kneedle.core.usecase.product.DraftProductService
import com.knitting.kneedle.core.usecase.product.ProductService
import com.knitting.kneedle.core.usecase.summary.ProductSummaryService
import com.knitting.kneedle.core.usecase.summary.ProfileSummaryService

interface ProductRepository :
    ProductService.ProductRepository,
    DraftProductService.ProductRepository,
    ProfileSummaryService.ProductRepository,
    ProductSummaryService.ProductRepository
