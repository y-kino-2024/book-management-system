package com.bookmanagementsystem.request.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * 書籍取得処理のリクエストオブジェクト
 *
 * @param bookId 書籍ID
 */
data class GetBookRequest(
    // リクエスト値にbookIdを定義しなかった場合にエラーメッセージを出すためにnull許容とする
    // リクエスト例)curl http://localhost:8080/getBook
    @field:NotBlank(message = "bookIdが未入力です。")
    @field:Size(min = 0, max = 8, message = "bookIdは8桁以内で入力してください。")
    val bookId: String?
)
