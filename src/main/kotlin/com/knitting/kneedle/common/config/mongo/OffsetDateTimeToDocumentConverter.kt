package com.knitting.kneedle.common.config.mongo

import org.bson.Document
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.util.Date

@Component
@WritingConverter
class OffsetDateTimeToDocumentConverter : Converter<OffsetDateTime, Document> {
    override fun convert(source: OffsetDateTime): Document? {
        val document = Document()
        document[DATE_TIME] = Date.from(source.toInstant())
        document[OFFSET] = source.toZonedDateTime().zone.id
        return document
    }

    companion object {
        private const val DATE_TIME = "dateTime"
        private const val OFFSET = "offset"
    }
}
