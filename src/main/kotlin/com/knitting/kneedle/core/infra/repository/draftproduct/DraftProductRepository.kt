package com.knitting.kneedle.core.infra.repository.draftproduct

import com.knitting.kneedle.core.usecase.product.DraftProductService
import com.knitting.kneedle.core.usecase.product.ProductService

interface DraftProductRepository : ProductService.DraftProductRepository, DraftProductService.DraftProductRepository
