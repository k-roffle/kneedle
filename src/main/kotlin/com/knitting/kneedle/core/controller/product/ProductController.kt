package com.knitting.kneedle.core.controller.product

import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.core.controller.helper.auth.Authenticated
import com.knitting.kneedle.core.controller.helper.auth.AuthenticatedUser
import com.knitting.kneedle.core.controller.helper.response.ResponseHelper.withJsonResponse
import com.knitting.kneedle.core.controller.product.dto.CreateProduct
import com.knitting.kneedle.core.controller.product.dto.UpdateProduct
import com.knitting.kneedle.core.controller.product.mapper.DraftProductResponseMapper
import com.knitting.kneedle.core.controller.product.mapper.ProductRequestMapper
import com.knitting.kneedle.core.controller.product.mapper.ProductResponseMapper
import com.knitting.kneedle.core.usecase.product.DraftProductService
import com.knitting.kneedle.core.usecase.product.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(
    private val productService: ProductService,
    private val draftProductService: DraftProductService,
) {
    @PostMapping
    suspend fun createProduct(
        @RequestBody body: CreateProduct.Request,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val request = ProductRequestMapper.toCreateProductData(body, user.knitterId)
        val result = productService.create(request)
        ProductResponseMapper.toCreateProductResponse(result)
    }

    @PutMapping("{productId}")
    suspend fun updateProduct(
        @PathVariable productId: String,
        @RequestBody body: UpdateProduct.Request,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val request = ProductRequestMapper.toUpdateProductData(body, productId, user.knitterId)
        val result = productService.update(request)
        ProductResponseMapper.toUpdateProductResponse(result)
    }

    @GetMapping("mine/{productId}")
    suspend fun getMyProduct(
        @PathVariable productId: String,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val request = ProductRequestMapper.toGetMyProductData(productId, user.knitterId)
        val result = productService.get(request)
        ProductResponseMapper.toGetMyProductResponse(result)
    }

    @GetMapping("mine")
    suspend fun getMyProducts(
        @Authenticated user: AuthenticatedUser,
        @RequestParam after: String?,
        @RequestParam count: Int?,
    ) = withJsonResponse {
        val request = ProductRequestMapper.toGetMyProductsData(Paging(after, count), user.knitterId)
        productService
            .get(request)
            .map(ProductResponseMapper::toGetMyProductsResponse)
    }

    @GetMapping("{productId}/draft/mine")
    suspend fun getMyDraftProductToUpdate(
        @PathVariable productId: String,
        @Authenticated user: AuthenticatedUser,
    ) = withJsonResponse {
        val draftProduct = draftProductService
            .getMyDraftProductToUpdate(productId = productId, knitterId = user.knitterId)
        DraftProductResponseMapper.toGetMyDraftProductToUpdateResponse(draftProduct)
    }
}
