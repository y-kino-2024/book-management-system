package com.bookmanagementsystem.request.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * 書籍登録処理のリクエストオブジェクト
 *
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 * @param operator 操作者
 */
data class CreateBookRequest(
    // 各項目、リクエスト値に定義しなかった場合にエラーメッセージを出すためにnull許容とする
    @field:NotBlank(message = "titleが未入力です。")
    @field:Size(min = 0, max = 256, message = "titleは256文字以内で入力してください。")
    val title: String?,
    val price: Int?,
    val publicationStatus: String?,
    val operator: String?
)
