package com.bookmanagementsystem.entity

import java.sql.Date

/**
 * 著者Entity
 *
 * @param id 筆者ID
 * @param authorName 筆者名
 * @param birthday 誕生日
 */
data class Author(
    val id: String,
    val authorName: String,
    val birthday: Date,
)
