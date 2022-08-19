package com.knitting.kneedle.core.infra.repository.product

import com.knitting.kneedle.common.pagination.Paginator
import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.common.pagination.Sort
import com.knitting.kneedle.common.pagination.SortDirection
import com.knitting.kneedle.core.domain.entity.Product
import com.knitting.kneedle.core.infra.document.ProductDocument
import com.knitting.kneedle.core.infra.document.toProductEntity
import com.knitting.kneedle.core.infra.repository.exception.NotFoundEntity
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val productRepository: ProductMongoRepository,
) : ProductRepository {
    private fun save(product: Product): Product =
        productRepository.save(product.toProductEntity()).toProduct()

    override fun createProduct(product: Product): Product = save(product)

    override fun updateProduct(product: Product): Product = save(product)

    override fun getProduct(id: String, knitterId: String): Product =
        productRepository
            .findByIdAndKnitterId(id, knitterId)
            ?.toProduct()
            ?: throw NotFoundEntity(ProductDocument::class.java)

    override fun getProductsByKnitterId(knitterId: String, paging: Paging, sort: Sort): List<Product> {
        val pageRequest = Paginator.makePageRequest(paging, sort)

        val productDocuments = when (sort.direction) {
            SortDirection.DESC ->
                if (paging.after != null) {
                    productRepository
                        .findAllByKnitterIdAndIdBefore(
                            knitterId = knitterId,
                            id = paging.after,
                            pageable = pageRequest,
                        )
                } else {
                    productRepository
                        .findAllByKnitterId(
                            knitterId = knitterId,
                            pageable = pageRequest,
                        )
                }
            else -> throw NotImplementedError()
        }

        return productDocuments.map { it.toProduct() }
    }

    override fun countMyProducts(knitterId: String): Int =
        productRepository.countByKnitterId(knitterId)

    override fun countPurchasedProducts(knitterId: String): Int {
        // TODO("Not yet implemented")
        return 0
    }

    override fun findRegisteredProduct(knitterId: String): List<Product> =
        productRepository.findAll().map { it.toProduct() }
}
