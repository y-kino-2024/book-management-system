package com.bookmanagementsystem.request.author

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

/**
 * 著者取得処理のリクエストオブジェクト
 *
 * @param authorId 著者ID
 */
data class GetAuthorRequest(
    // authorIdに文字が入れられた場合にエラーメッセージを出すためにStringとする
    // リクエスト値にauthorIdを定義しなかった場合にエラーメッセージを出すためにnull許容とする
    // リクエスト例)curl http://localhost:8080/getAuthor
    @field:NotBlank(message = "authorIdが未入力です。")
    @field:Pattern(regexp = "^\\d{0,8}\$", message = "authorIdは8桁以下の数値で入力してください。")
    val authorId: String?
)
