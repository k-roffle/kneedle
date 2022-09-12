package com.knitting.kneedle.core.infra.repository.designer

import com.knitting.kneedle.core.domain.entity.Designer
import com.knitting.kneedle.core.infra.document.toDesignerEntity
import org.springframework.stereotype.Repository

@Repository
class DesignerRepositoryImpl(
    private val designerRepository: DesignerMongoRepository,
) : DesignerRepository {
    override fun create(designer: Designer): Designer =
        designerRepository
            .save(designer.toDesignerEntity())
            .toDesigner()

    override fun findByKnitterId(knitterId: String): Designer? =
        designerRepository
            .findByKnitterId(knitterId)
            ?.toDesigner()
}
