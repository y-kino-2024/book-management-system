package com.bookmanagementsystem.validator

import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * 共通バリデーションクラス
 */
@SpringBootApplication
class CommonValidator {

    companion object {
        // 操作者の最大文字数
        const val OPERATOR_MAX = 256
    }

    /**
     * 作成処理の共通チェック
     * @args operator 操作者
     */
    fun validCreate(operator: String?) {
        checkOperator(operator)
    }

    /**
     * 更新処理の共通チェック
     * @args operator 操作者
     */
    fun validUpdate(operator: String?) {
        checkOperator(operator)
    }


    /**
     * 操作者のチェック
     * @args operator 操作者
     */
    private fun checkOperator(operator: String?) {
        // 必須チェック
        if (operator.isNullOrBlank()) {
            throw NullPointerException("操作者が未入力です。")
        }
        // 桁数チェック(DB定義：256桁)
        if (operator.length > OPERATOR_MAX) {
            throw IllegalStateException("操作者は256文字以内で入力してください。")
        }
    }
}
