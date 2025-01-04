package com.bookmanagementsystem.dto

import java.time.LocalDateTime

/**
 * author_infoテーブルのDto
 *
 * @param bookId 書籍ID
 * @param authorId 著者ID
 * @param createdBy 作成者
 * @param createdAt 作成日時
 * @param updatedBy 更新者
 * @param updatedAt 更新日時
 * @param deleteFlg 削除フラグ
 */
data class AuthorIndexDto(
    val bookId: Int,
    val authorId: Int,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val updatedBy: String,
    val updatedAt: LocalDateTime,
    val deleteFlg: String,
)
