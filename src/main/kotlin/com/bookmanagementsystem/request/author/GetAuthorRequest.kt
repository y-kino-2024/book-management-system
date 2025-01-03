package com.bookmanagementsystem.request.author

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * 著者取得処理のリクエストオブジェクト
 * @param authorId 著者ID
 */
data class GetAuthorRequest(
    // リクエスト値にauthorIdを定義しなかった場合にエラーメッセージを出すためにnull許容とする
    // リクエスト例)curl http://localhost:8080/getAuthor
    @field:NotBlank(message = "authorIdが未入力です。")
    @field:Size(min = 0, max = 8, message = "authorIdは8桁以内で入力してください。")
    val authorId: String?
)
