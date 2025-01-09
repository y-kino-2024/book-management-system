package com.bookmanagementsystem.request.author

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

/**
 * 著者更新処理のリクエストオブジェクト
 *
 * @param authorId 著者ID
 * @param authorName 著者名
 * @param birthday 誕生日
 * @param operator 操作者
 * @param deleteFlg 削除フラグ
 */
data class UpdateAuthorRequest(
    // authorId以外はnull許容(nullの項目は未変更扱い)。
    // リクエスト値にauthorIdを定義しなかった場合にエラーメッセージを出すためにnull許容とする
    // authorIdに文字が入れられた場合にエラーメッセージを出すためにStringとする
    @field:NotBlank(message = "authorIdが未入力です。")
    @field:Pattern(
        regexp = "^\\d{0,8}\$",
        message = "authorIdは8桁以下の数値で入力してください。"
    )
    val authorId: String?,
    @field:Size(min = 0, max = 256, message = "authorNameは256文字以内で入力してください。")
    val authorName: String?,
    // birthdayにYYYY-MM-DD形式の日付以外が入れられた場合にエラーメッセージを出すためにStringとする
    @field:Pattern(
        regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])\$",
        message = "birthdayはYYYY-MM-DD形式で入力してください。"
    )
    val birthday: String?,
    @field:Size(min = 0, max = 256, message = "operatorは256文字以内で入力してください。")
    val operator: String,
    @field:Size(min = 0, max = 1, message = "deleteFlgは1桁で入力してください。")
    val deleteFlg: String?
)
