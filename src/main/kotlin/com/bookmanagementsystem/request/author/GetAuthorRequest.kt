package com.bookmanagementsystem.request.author

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull

/**
 * 著者取得処理のリクエストオブジェクト
 *
 * @param authorId 著者ID
 */
data class GetAuthorRequest(
    // リクエスト値にauthorIdを定義しなかった場合にエラーメッセージを出すためにnull許容とする
    // リクエスト例)curl http://localhost:8080/getAuthor
    @field:NotNull(message = "authorIdが未入力です。")
    @field:Max(value = 99999999, message = "authorIdは8桁以内で入力してください。")
    val authorId: Int?
)
