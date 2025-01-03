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
 * @param operator 操作者
 * @param deleteFlg 削除フラグ
 */
data class Book(
    val id: String?,
    val authorId: List<String>?,
    val title: String?,
    val price: String?,
    val publicationStatus: PublicationStatus?,
    val operator: String?,
    val deleteFlg: String?,
)
