package com.knitting.kneedle.common.config.mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions

@Configuration
@EnableConfigurationProperties(MongoConfig.MongoProperties::class)
class MongoConfig(
    private val properties: MongoProperties,
    private val documentToOffsetDateTimeConverter: DocumentToOffsetDateTimeConverter,
    private val offsetDateTimeToDocumentConverter: OffsetDateTimeToDocumentConverter,
) : AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String = properties.database

    override fun customConversions(): MongoCustomConversions {
        return MongoCustomConversions(
            listOf(
                documentToOffsetDateTimeConverter,
                offsetDateTimeToDocumentConverter,
            )
        )
    }

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(properties.uri)
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(mongoClientSettings)
    }

    @ConstructorBinding
    @ConfigurationProperties("spring.data.mongodb")
    data class MongoProperties(
        val uri: String,
        val database: String,
    )
}
