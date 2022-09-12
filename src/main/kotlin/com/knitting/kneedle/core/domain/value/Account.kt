package com.knitting.kneedle.core.domain.value

/**
 * 계좌
 *
 * @property bank 은행
 * @property number 계좌번호
 * @property holderName 예금주 이름
 */
data class Account(
    val bank: Bank,
    val number: String,
    val holderName: String,
) {
    enum class Bank(name: String) {
        HANA("KEB하나은행"),
        STANDARD_CHARTERED("SC제일은행"),
        KB("국민은행"),
        SHINHAN("신한은행"),
        WOORI("우리은행"),
        CITI("한국시티은행"),
        IBK("기업은행"),
        NONGHYUP("농협"),
        SUHYUP("수협"),
    }
}
