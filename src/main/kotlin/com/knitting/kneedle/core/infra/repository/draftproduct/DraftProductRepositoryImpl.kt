package com.knitting.kneedle.core.infra.repository.draftproduct

import com.knitting.kneedle.core.domain.entity.DraftProduct
import com.knitting.kneedle.core.infra.document.DraftProductDocument
import com.knitting.kneedle.core.infra.document.toDraftProductEntity
import com.knitting.kneedle.core.infra.repository.exception.NotFoundEntity
import org.springframework.stereotype.Repository

@Repository
class DraftProductRepositoryImpl(
    private val draftProductRepository: DraftProductMongoRepository,
) : DraftProductRepository {
    override fun getDraftProduct(id: String, knitterId: String): DraftProduct =
        draftProductRepository
            .findByIdAndKnitterId(id, knitterId)
            ?.toDraftProduct()
            ?: throw NotFoundEntity(DraftProductDocument::class.java)

    override fun delete(draftProduct: DraftProduct): String {
        draftProductRepository.delete(draftProduct.toDraftProductEntity())
        return draftProduct.id
    }

    override fun findDraftProductsToCreate(knitterId: String): List<DraftProduct> =
        draftProductRepository
            .findAllByKnitterIdAndProductId(knitterId, null)
            .map { it.toDraftProduct() }

    override fun getDraftProductToUpdate(productId: String, knitterId: String): DraftProduct =
        draftProductRepository
            .findByKnitterIdAndProductId(knitterId, productId)
            ?.toDraftProduct()
            ?: throw NotFoundEntity(DraftProductDocument::class.java)

    private fun save(draftProduct: DraftProduct): DraftProduct =
        draftProductRepository
            .save(draftProduct.toDraftProductEntity())
            .toDraftProduct()

    override fun create(draftProduct: DraftProduct): DraftProduct = save(draftProduct)

    override fun update(draftProduct: DraftProduct): DraftProduct = save(draftProduct)
}
