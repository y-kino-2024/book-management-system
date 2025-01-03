package com.bookmanagementsystem.entity

import java.time.LocalDate

/**
 * 著者Entity
 *
 * @param id 筆者ID
 * @param authorName 筆者名
 * @param birthday 誕生日
 * @param operator 操作者
 */
data class Author(
    val id: Int?,
    val authorName: String?,
    val birthday: LocalDate?,
    val operator: String?,
    val deleteFlg: String?,
)
