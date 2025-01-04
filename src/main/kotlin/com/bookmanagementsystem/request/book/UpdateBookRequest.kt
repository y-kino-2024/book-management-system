package com.bookmanagementsystem.request.book

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
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
    @field:NotNull(message = "authorIdListが未入力です。")
    val authorIdList: List<Int>?,
    @field:NotNull(message = "bookIdが未入力です。")
    @field:Max(value = 99999999, message = "bookIdは8桁以内で入力してください。")
    val bookId: Int?,
    @field:Size(min = 0, max = 256, message = "titleは256文字以内で入力してください。")
    val title: String?,
    val price: Double?,
    val publicationStatus: String?,
    @field:NotBlank(message = "operatorが未入力です。")
    @field:Size(min = 0, max = 256, message = "operatorは256文字以内で入力してください。")
    val operator: String?,
    @field:Size(min = 0, max = 1, message = "deleteFlgは1桁で入力してください。")
    val deleteFlg: String?
)
