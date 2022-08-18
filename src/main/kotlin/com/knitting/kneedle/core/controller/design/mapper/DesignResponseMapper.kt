package com.knitting.kneedle.core.controller.design.mapper

import com.knitting.kneedle.core.controller.design.dto.MyDesign
import com.knitting.kneedle.core.controller.design.dto.MyDesigns
import com.knitting.kneedle.core.controller.design.dto.NewDesign
import com.knitting.kneedle.core.controller.design.dto.SizeDto
import com.knitting.kneedle.core.controller.design.dto.UpdateDesign
import com.knitting.kneedle.core.domain.entity.Design
import com.knitting.kneedle.core.domain.value.unit.Size

object DesignResponseMapper {
    fun toNewDesignResponse(design: Design): NewDesign.Response =
        with(design) {
            NewDesign.Response(id = id)
        }

    fun toUpdateDesignResponse(design: Design): UpdateDesign.Response =
        with(design) {
            UpdateDesign.Response(id = id)
        }

    fun toMyDesignsResponse(design: Design): MyDesigns.Response =
        with(design) {
            MyDesigns.Response(
                id = id,
                name = name,
                yarn = yarn,
                coverImageUrl = coverImageUrl,
                tags = listOf(designType.tag, patternType.tag),
                price = price.value,
                createdAt = createdAt!!,
            )
        }

    fun toMyDesignResponse(design: Design): MyDesign.Response =
        with(design) {
            MyDesign.Response(
                id = id,
                name = name,
                designType = designType,
                patternType = patternType,
                gauge = gauge,
                size = toDtoFromDomain(size),
                needle = needle,
                yarn = yarn,
                extra = extra,
                price = price,
                pattern = pattern,
                description = description,
                targetLevel = targetLevel,
                coverImageUrl = coverImageUrl,
                techniques = techniques,
                updatedAt = updatedAt,
                createdAt = createdAt,
            )
        }

    private fun toDtoFromDomain(size: Size): SizeDto =
        with(size) {
            SizeDto(
                sizeUnit = totalLength.unit,
                totalLength = totalLength.value,
                sleeveLength = sleeveLength.value,
                shoulderWidth = shoulderWidth.value,
                bottomWidth = bottomWidth.value,
                armholeDepth = armholeDepth.value,
            )
        }
}
