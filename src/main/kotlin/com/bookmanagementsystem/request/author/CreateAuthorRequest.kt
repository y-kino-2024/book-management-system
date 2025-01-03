package com.bookmanagementsystem.request.author

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

/**
 * 著者登録処理のリクエストオブジェクト
 *
 * @param authorName 著者名
 * @param birthday 誕生日
 * @param operator 操作者
 */
data class CreateAuthorRequest(
    // 各項目、リクエスト値に定義しなかった場合にエラーメッセージを出すためにnull許容とする
    @field:NotBlank(message = "authorNameが未入力です。")
    @field:Size(min = 0, max = 256, message = "authorNameは256文字以内で入力してください。")
    val authorName: String?,
    @field:NotBlank(message = "birthdayが未入力です。")
    val birthday: LocalDate?,
    @field:NotBlank(message = "operatorが未入力です。")
    @field:Size(min = 0, max = 256, message = "operatorは256文字以内で入力してください。")
    val operator: String?
)
