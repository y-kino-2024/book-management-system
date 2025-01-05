package com.bookmanagementsystem.entity

import com.bookmanagementsystem.enumkt.PublicationStatus

/**
 * 書籍Entity
 *
 * @param id 書籍ID
 * @param authorIdList 著者IDリスト
 * @param title タイトル
 * @param price 価格
 * @param publicationStatus 出版状況
 * @param operator 操作者
 * @param deleteFlg 削除フラグ
 */
data class Book(
    // 登録・更新処理を考慮し、各項目をnull許容とした(nullの場合は更新対象外)
    val id: Int?,
    val authorIdList: List<Int>?,
    val title: String?,
    val price: Double?,
    val publicationStatus: PublicationStatus?,
    val operator: String?,
    val deleteFlg: String?,
)
