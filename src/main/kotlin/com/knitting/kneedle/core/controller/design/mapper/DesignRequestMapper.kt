package com.knitting.kneedle.core.controller.design.mapper

import com.knitting.kneedle.common.pagination.Paging
import com.knitting.kneedle.common.pagination.Sort
import com.knitting.kneedle.common.pagination.SortDirection
import com.knitting.kneedle.core.controller.design.dto.NewDesign
import com.knitting.kneedle.core.controller.design.dto.SizeDto
import com.knitting.kneedle.core.controller.design.dto.UpdateDesign
import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.Pattern
import com.knitting.kneedle.core.domain.value.Technique
import com.knitting.kneedle.core.domain.value.unit.Gauge
import com.knitting.kneedle.core.domain.value.unit.Length
import com.knitting.kneedle.core.domain.value.unit.Size
import com.knitting.kneedle.core.usecase.design.dto.CreateDesignData
import com.knitting.kneedle.core.usecase.design.dto.GetMyDesignData
import com.knitting.kneedle.core.usecase.design.dto.MyDesignFilter
import com.knitting.kneedle.core.usecase.design.dto.UpdateDesignData

object DesignRequestMapper {
    fun toCreateDesignData(data: NewDesign.Request, knitterId: String): CreateDesignData =
        with(data) {
            CreateDesignData(
                knitterId = knitterId,
                name = name,
                designType = designType,
                patternType = patternType,
                gauge = Gauge(
                    stitches = stitches,
                    rows = rows,
                ),
                size = toDomainFromDto(size),
                needle = needle,
                yarn = yarn,
                extra = extra,
                price = Money(price),
                pattern = Pattern(pattern),
                description = description,
                targetLevel = targetLevel,
                coverImageUrl = coverImageUrl,
                techniques = techniques.map { technique -> Technique(technique) },
                draftId = draftId,
            )
        }

    fun toUpdateDesignData(data: UpdateDesign.Request, designId: String, knitterId: String): UpdateDesignData =
        with(data) {
            UpdateDesignData(
                id = designId,
                knitterId = knitterId,
                designType = designType,
                patternType = patternType,
                gauge = Gauge(
                    stitches = stitches,
                    rows = rows,
                ),
                size = toDomainFromDto(size),
                needle = needle,
                yarn = yarn,
                extra = extra,
                pattern = Pattern(pattern),
                description = description,
                targetLevel = targetLevel,
                techniques = techniques.map { Technique(it) },
                draftId = draftId,
            )
        }

    fun toMyDesignFilter(paging: Paging, knitterId: String) =
        MyDesignFilter(
            knitterId,
            paging,
            Sort("id", SortDirection.DESC),
        )

    fun toGetMyDesignData(designId: String, knitterId: String) =
        GetMyDesignData(
            id = designId,
            knitterId = knitterId,
        )

    private fun toDomainFromDto(size: SizeDto): Size =
        with(size) {
            Size(
                totalLength = Length(value = totalLength, unit = sizeUnit),
                sleeveLength = Length(value = sleeveLength, unit = sizeUnit),
                shoulderWidth = Length(value = shoulderWidth, unit = sizeUnit),
                bottomWidth = Length(value = bottomWidth, unit = sizeUnit),
                armholeDepth = Length(value = armholeDepth, unit = sizeUnit),
            )
        }
}
