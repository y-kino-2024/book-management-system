package com.bookmanagementsystem.request.book

/**
 * 著者から書籍を取得する処理のリクエストオブジェクト
 * @param authorId 著者ID
 */
data class GetBookFromAuthorRequest(
    val authorId: String?
)
