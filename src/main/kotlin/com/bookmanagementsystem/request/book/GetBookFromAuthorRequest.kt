package com.bookmanagementsystem.request.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

/**
 * 著者から書籍を取得する処理のリクエストオブジェクト
 *
 * @param authorId 著者ID
 */
data class GetBookFromAuthorRequest(
    // リクエスト値にauthorIdを定義しなかった場合にエラーメッセージを出すためにnull許容とする
    // リクエスト例)curl http://localhost:8080/getBookFromAuthor
    @field:NotBlank(message = "authorIdが未入力です。")
    @field:Pattern(
        regexp = "^\\d{0,8}\$",
        message = "authorIdは8桁以下の数値で入力してください。"
    )
    val authorId: String?
)
