package com.knitting.kneedle.core.domain.entity

import com.knitting.kneedle.core.domain.value.Money
import com.knitting.kneedle.core.domain.value.Pattern
import com.knitting.kneedle.core.domain.value.Technique
import com.knitting.kneedle.core.domain.value.unit.Gauge
import com.knitting.kneedle.core.domain.value.unit.Size
import java.time.OffsetDateTime

/**
 * 도안
 *
 * @property id 식별자
 * @property knitterId 사용자 id
 * @property name 도안 이름
 * @property designType 편물 종류
 * @property patternType 도안 종류 (ex> 서술형, 이미지 ..)
 * @property gauge 사용된 게이지
 * @property size 편물 사이즈
 * @property needle 사용한 바늘
 * @property yarn 사용한 실
 * @property extra 추가재료
 * @property price 도안 가격
 * @property pattern 도안 내용
 * @property description 도안 한 줄 소개
 * @property targetLevel 난이도
 * @property coverImageUrl 대표 이미지
 * @property techniques 사용된 기법
 * @property updatedAt 수정 시각
 * @property createdAt 생성 시각
 */
data class Design(
    val id: String,
    val knitterId: String,
    val name: String,
    val designType: DesignType,
    val patternType: PatternType,
    val gauge: Gauge,
    val size: Size?,
    val needle: String,
    val yarn: String?,
    val extra: String?,
    val price: Money,
    val pattern: Pattern,
    val description: String?,
    val targetLevel: LevelType?,
    val coverImageUrl: String,
    val techniques: List<Technique>?,
    val updatedAt: OffsetDateTime?,
    val createdAt: OffsetDateTime?,
) {
    fun update(
        designType: DesignType,
        patternType: PatternType,
        gauge: Gauge,
        size: Size?,
        needle: String,
        yarn: String?,
        extra: String?,
        pattern: Pattern,
        description: String?,
        targetLevel: LevelType?,
        techniques: List<Technique>?,
    ) = this.copy(
        designType = designType,
        patternType = patternType,
        gauge = gauge,
        size = size,
        needle = needle,
        yarn = yarn,
        extra = extra,
        pattern = pattern,
        description = description,
        targetLevel = targetLevel,
        techniques = techniques,
        updatedAt = OffsetDateTime.now(),
    )

    enum class DesignType(val code: Int, val tag: String) {
        Sweater(1, "니트"),
    }

    enum class PatternType(val code: Int, val tag: String) {
        Text(1, "서술형도안"),
        Image(2, "이미지도안"),
        Video(3, "영상도안"),
    }

    enum class LevelType {
        PERSON_BY_PERSON,
        EASY,
        NORMAL,
        HARD
    }

    companion object {
        fun new(
            knitterId: String,
            name: String,
            designType: DesignType,
            patternType: PatternType,
            gauge: Gauge,
            size: Size?,
            needle: String,
            yarn: String?,
            extra: String?,
            price: Money,
            pattern: Pattern,
            description: String?,
            targetLevel: LevelType?,
            coverImageUrl: String,
            techniques: List<Technique>?,
        ) = Design(
            id = "",
            knitterId = knitterId,
            name = name,
            designType = designType,
            patternType = patternType,
            gauge = gauge,
            size = size,
            needle = needle,
            yarn = yarn,
            extra = extra,
            price = price,
            pattern = pattern,
            description = description,
            targetLevel = targetLevel,
            coverImageUrl = coverImageUrl,
            techniques = techniques,
            updatedAt = null,
            createdAt = null,
        )
    }
}
