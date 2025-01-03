package com.bookmanagementsystem.dto

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * author_infoテーブルのDto
 *
 * @param id 筆者ID
 * @param authorName 筆者名
 * @param birthday 誕生日
 * @param createdBy 作成者
 * @param createdAt 作成日時
 * @param updatedBy 更新者
 * @param updatedAt 更新日時
 * @param deleteFlg 削除フラグ
 */
data class AuthorsInfoDto(
    val id: Int?,
    val authorName: String,
    val birthday: LocalDate,
    val createdBy: String,
    val createdAt: LocalDateTime,
    val updatedBy: String,
    val updatedAt: LocalDateTime,
    val deleteFlg: String,
)
