package com.knitting.kneedle.common.config.mongo

import org.bson.Document
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.time.ZoneId

@Component
@WritingConverter
class DocumentToOffsetDateTimeConverter : Converter<Document, OffsetDateTime> {
    override fun convert(source: Document): OffsetDateTime? {
        val dateTime = source.getDate(DATE_TIME)
        val offset: String = source.getString(OFFSET)
        return OffsetDateTime.ofInstant(dateTime.toInstant(), ZoneId.of(offset))
    }

    companion object {
        private const val DATE_TIME = "dateTime"
        private const val OFFSET = "offset"
    }
}
