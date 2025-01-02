package com.bookmanagementsystem.response.book

import java.time.LocalDate

/**
 * 著者から書籍を取得する処理のレスポンスオブジェクト
 * @param bookId 書籍ID
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 */
data class GetBookFromAuthorResponse(
    val bookId: String,
    val title: String,
    val price: LocalDate,
    val publicationStatus: String,
)
