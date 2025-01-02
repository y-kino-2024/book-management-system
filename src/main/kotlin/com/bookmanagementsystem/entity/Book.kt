package com.bookmanagementsystem.entity

import com.bookmanagementsystem.enum.PublicationStatus

/**
 * 書籍Entity
 *
 * @param id 書籍ID
 * @param authorId 筆者ID
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 */
data class Book(
    val id: String?,
    val authorId: List<String>,
    val title: String,
    val price: String,
    val publicationStatus: PublicationStatus,
)
