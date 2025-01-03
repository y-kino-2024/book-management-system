package com.bookmanagementsystem.request.book

/**
 * 書籍取得処理のリクエストオブジェクト
 * @param bookId 書籍ID
 */
data class GetBookRequest(
    val bookId: String?
)
