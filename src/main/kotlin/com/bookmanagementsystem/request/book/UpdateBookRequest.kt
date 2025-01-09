package com.bookmanagementsystem.request.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

/**
 * 書籍更新処理のリクエストオブジェクト
 *
 * @param authorIdList 著者リスト
 * @param bookId 書籍ID
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 * @param operator 操作者
 * @param deleteFlg 削除フラグ
 */
data class UpdateBookRequest(
    // bookId以外はnull許容(nullの項目は未変更扱い)。
    // リクエスト値にbookIdを定義しなかった場合にエラーメッセージを出すためにnull許容とする
    @field:NotBlank(message = "bookIdが未入力です。")
    @field:Pattern(
        regexp = "^\\d{0,8}\$",
        message = "bookIdは8桁以下の数値で入力してください。"
    )
    val bookId: String?,
    @field:NotNull(message = "authorIdListが未入力です。")
    val authorIdList: List<String>?,
    @field:Size(min = 0, max = 256, message = "titleは256文字以内で入力してください。")
    val title: String?,
    @field:Pattern(
        regexp = "^\\d{0,12}\$",
        message = "priceは12桁以下の数値で入力してください。"
    )
    val price: String?,
    val publicationStatus: String?,
    @field:NotBlank(message = "operatorが未入力です。")
    @field:Size(min = 0, max = 256, message = "operatorは256文字以内で入力してください。")
    val operator: String?,
    @field:Size(min = 0, max = 1, message = "deleteFlgは1桁で入力してください。")
    val deleteFlg: String?
)
