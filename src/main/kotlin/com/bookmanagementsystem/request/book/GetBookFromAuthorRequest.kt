package com.bookmanagementsystem.request.book

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 * 著者から書籍を取得する処理のリクエストオブジェクト
 *
 * @param authorId 著者ID
 */
data class GetBookFromAuthorRequest(
    // リクエスト値にauthorIdを定義しなかった場合にエラーメッセージを出すためにnull許容とする
    // リクエスト例)curl http://localhost:8080/getBookFromAuthor
    @field:NotNull(message = "authorIdが未入力です。")
    @field:Size(min = 0, max = 8, message = "authorIdは8桁以内で入力してください。")
    val authorId: Int?
)
