package com.bookmanagementsystem.request.book

/**
 * 書籍更新処理のリクエストオブジェクト
 * @param bookId 書籍ID
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 * @param operator 操作者
 */
data class UpdateBookRequest(
    val bookId: String?,
    val title: String?,
    val price: Int?,
    val publicationStatus: String?,
    val operator: String?
)
