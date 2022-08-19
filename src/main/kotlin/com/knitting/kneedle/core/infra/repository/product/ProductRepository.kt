package com.knitting.kneedle.core.infra.repository.product

import com.knitting.kneedle.core.usecase.product.DraftProductService
import com.knitting.kneedle.core.usecase.product.ProductService

interface ProductRepository : ProductService.ProductRepository, DraftProductService.ProductRepository
