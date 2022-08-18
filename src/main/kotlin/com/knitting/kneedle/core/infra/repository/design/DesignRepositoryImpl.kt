package com.knitting.kneedle.core.infra.repository.design

import com.knitting.kneedle.common.pagination.Paginator
import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.common.pagination.Sort
import com.knitting.kneedle.common.pagination.SortDirection
import com.knitting.kneedle.core.domain.entity.Design
import com.knitting.kneedle.core.infra.document.DesignDocument
import com.knitting.kneedle.core.infra.document.toDesignEntity
import com.knitting.kneedle.core.infra.repository.exception.NotFoundEntity
import org.springframework.stereotype.Repository

@Repository
class DesignRepositoryImpl(
    private val designRepository: DesignMongoRepository,
) : DesignRepository {
    override fun getDesign(id: String, knitterId: String): Design =
        designRepository.findByIdAndKnitterId(id, knitterId)?.toDesign()
            ?: throw NotFoundEntity(DesignDocument::class.java)

    private fun saveDesign(design: Design): Design =
        designRepository.save(design.toDesignEntity()).toDesign()

    override fun createDesign(design: Design): Design = saveDesign(design)

    override fun updateDesign(design: Design): Design = saveDesign(design)

    override fun getDesignsByKnitterId(knitterId: String, paging: Paging, sort: Sort): List<Design> {
        val pageRequest = Paginator.makePageRequest(paging, sort)
        val designs: List<DesignDocument> = when (sort.direction) {
            SortDirection.DESC ->
                if (paging.after != null) {
                    designRepository
                        .findAllByKnitterIdAndIdBefore(
                            knitterId = knitterId,
                            id = paging.after,
                            pageable = pageRequest,
                        )
                } else {
                    designRepository
                        .findAllByKnitterId(
                            knitterId = knitterId,
                            pageable = pageRequest,
                        )
                }
            else -> throw NotImplementedError()
        }
        return designs.map(DesignDocument::toDesign)
    }
}
