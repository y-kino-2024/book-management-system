package com.bookmanagementsystem.dto

import java.time.LocalDateTime

/**
 * book_infoテーブルのDto
 *
 * @param id 書籍ID
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 * @param createdBy 作成者
 * @param createdAt 作成日時
 * @param updatedBy 更新者
 * @param updatedAt 更新日時
 * @param deleteFlg 削除フラグ
 */
data class BooksInfoDto(
    val id: String?,
    val title: String,
    val price: String,
    val publicationStatus: String,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val updatedBy: String,
    val updatedAt: LocalDateTime,
    val deleteFlg: String,
)
