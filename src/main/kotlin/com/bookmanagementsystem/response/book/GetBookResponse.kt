package com.bookmanagementsystem.response.book

/**
 * 書籍取得処理のレスポンスオブジェクト
 *
 * @param bookId 書籍ID
 * @param authorIdList 著者IDリスト
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 */
data class GetBookResponse(
    val bookId: Int,
    val authorIdList: List<String>,
    val title: String,
    val price: Int,
    val publicationStatus: String,
)
