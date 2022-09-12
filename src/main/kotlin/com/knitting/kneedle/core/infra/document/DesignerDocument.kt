package com.knitting.kneedle.core.infra.document

import com.knitting.kneedle.core.domain.entity.Designer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.OffsetDateTime
import com.knitting.kneedle.core.domain.value.Account as AccountValue

@Document(collection = "designer")
class DesignerDocument(
    @Id
    @MongoId(value = FieldType.OBJECT_ID)
    val id: String,
    private val knitterId: String,
    private val nickname: String,
    private val selfIntroduction: String,
    private val account: Account,
    private val updatedAt: OffsetDateTime = OffsetDateTime.now(),
    private val createdAt: OffsetDateTime = OffsetDateTime.now(),
) {
    fun toDesigner(): Designer =
        Designer(
            id = this.id,
            knitterId = this.knitterId,
            nickname = this.nickname,
            selfIntroduction = this.selfIntroduction,
            account = AccountValue(
                bank = AccountValue.Bank.valueOf(account.bank),
                number = account.number,
                holderName = account.holderName,
            ),
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
        )

    data class Account(
        val bank: String,
        val number: String,
        val holderName: String,
    )
}

fun Designer.toDesignerEntity() =
    DesignerDocument(
        id = this.id.ifBlank { ObjectId().toHexString() },
        knitterId = this.knitterId,
        nickname = this.nickname,
        selfIntroduction = this.selfIntroduction,
        account = DesignerDocument.Account(
            bank = account.bank.name,
            number = account.number,
            holderName = account.holderName,
        ),
        updatedAt = this.updatedAt ?: OffsetDateTime.now(),
        createdAt = this.createdAt ?: OffsetDateTime.now(),
    )
