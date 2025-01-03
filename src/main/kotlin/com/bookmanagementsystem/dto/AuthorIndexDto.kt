package com.bookmanagementsystem.dto

import java.sql.Timestamp

/**
 * author_infoテーブルのDto
 *
 * @param bookId 書籍ID
 * @param authorId 筆者ID
 * @param createdBy 作成者
 * @param createdAt 作成日時
 * @param updatedBy 更新者
 * @param updatedAt 更新日時
 * @param deleteFlg 削除フラグ
 */
data class AuthorIndexDto(
    val bookId: String,
    val authorId: String,
    val createdBy: String,
    val createdAt: Timestamp,
    val updatedBy: String,
    val updatedAt: Timestamp,
    val deleteFlg: String,
)
