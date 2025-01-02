package com.bookmanagementsystem.request.author

/**
 * 著者取得処理のリクエストオブジェクト
 * @param authorId 著者ID
 */
data class GetAuthorRequest(
    val authorId: String?
)
