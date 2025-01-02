package com.bookmanagementsystem.request.book

/**
 * 書籍登録処理のリクエストオブジェクト
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 * @param operator 操作者
 */
data class CreateBookRequest(
    val title: String?,
    val price: Int?,
    val publicationStatus: String?,
    val operator: String?
)
