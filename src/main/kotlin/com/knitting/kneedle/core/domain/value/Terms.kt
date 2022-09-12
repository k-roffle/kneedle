package com.knitting.kneedle.core.domain.value

/**
 * 약관
 *
 * @property key 식별자
 * @property content 약관 내용
 */
enum class Terms(val key: String, val content: String) {
    RECEIVE_QUESTION_TO_ACCOUNT_MAIL(
        "receive-question-to-account-mail",
        "가입한 구글 메일을 통해 문의를 받는 것에 동의해요.",
    ),
    GIVE_A_REVENUE_COMMISSION(
        "give-a-revenue-commission",
        "판매 수익의 5%를 플랫폼 니팅에게 제공하는 것에 동의해요."
    );

    companion object {
        fun findByKey(key: String): Terms {
            return values().find { it.key == key }
                ?: throw NoSuchElementException("Terms(key=$key) is not exists.")
        }
    }
}
