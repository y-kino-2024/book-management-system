package com.bookmanagementsystem.request.author

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

/**
 * 著者更新処理のリクエストオブジェクト
 * @param authorId 著者ID
 * @param authorName 著者名
 * @param birthday 誕生日
 * @param operator 操作者
 * @param deleteFlg 削除フラグ
 */
data class UpdateAuthorRequest(
    // authorId以外はnull許容(nullの項目は未変更扱い)。
    // authorIdは別途validatorクラスでnullチェックを行い、未定義の場合エラーメッセージを出す。
    @field:NotBlank(message = "authorNameが未入力です。")
    @field:Size(min = 0, max = 256, message = "authorNameは256文字以内で入力してください。")
    val authorId: String?,
    @field:Size(min = 0, max = 256, message = "authorNameは256文字以内で入力してください。")
    val authorName: String?,
    val birthday: LocalDate?,
    @field:Size(min = 0, max = 256, message = "operatorは256文字以内で入力してください。")
    val operator: String?,
    @field:Size(min = 0, max = 1, message = "deleteFlgは1桁で入力してください。")
    val deleteFlg: String?
)
