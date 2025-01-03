package com.bookmanagementsystem.validator

import org.springframework.stereotype.Component

/**
 * 共通バリデーションクラス
 */
@Component
class CommonValidator {

    /**
     * 操作者のチェック
     *
     * @args operator 操作者
     */
    fun checkOperator(operator: String?) {
        // 必須チェック
        if (operator.isNullOrBlank()) {
            throw NullPointerException("operatorが未入力です。")
        }
    }
}
