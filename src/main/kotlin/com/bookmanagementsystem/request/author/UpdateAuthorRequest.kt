package com.bookmanagementsystem.request.author

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

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
    @field:NotNull(message = "authorIdが未入力です。")
    @field:Max(value = 99999999, message = "authorIdは8文字以内で入力してください。")
    val authorId: Int?,
    @field:Size(min = 0, max = 256, message = "authorNameは256文字以内で入力してください。")
    val authorName: String?,
    val birthday: LocalDate?,
    @field:Size(min = 0, max = 256, message = "operatorは256文字以内で入力してください。")
    val operator: String,
    @field:Size(min = 0, max = 1, message = "deleteFlgは1桁で入力してください。")
    val deleteFlg: String?
)
