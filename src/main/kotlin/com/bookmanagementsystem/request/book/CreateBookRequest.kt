package com.bookmanagementsystem.request.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

/**
 * 書籍登録処理のリクエストオブジェクト
 *
 * @param authorIdList 著者リスト
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 * @param operator 操作者
 */
data class CreateBookRequest(
    // 各項目、リクエスト値に定義しなかった場合にエラーメッセージを出すためにnull許容とする
    @field:NotNull(message = "authorIdListが未入力です。")
    val authorIdList: List<String>?,
    @field:NotBlank(message = "titleが未入力です。")
    @field:Size(min = 0, max = 256, message = "titleは256文字以内で入力してください。")
    val title: String?,
    @field:NotBlank(message = "priceが未入力です。")
    @field:Pattern(
        regexp = "^\\d{0,12}\$",
        message = "priceは12桁以下の数値で入力してください。"
    )
    val price: String?,
    @field:NotBlank(message = "publicationStatusが未入力です。")
    val publicationStatus: String?,
    @field:NotBlank(message = "operatorが未入力です。")
    @field:Size(min = 0, max = 256, message = "operatorは256文字以内で入力してください。")
    val operator: String?
)
