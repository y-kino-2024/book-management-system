package com.bookmanagementsystem.entity

import java.time.LocalDate

/**
 * 著者Entity
 *
 * @param id 著者ID
 * @param authorName 著者名
 * @param birthday 誕生日
 * @param operator 操作者
 * @param deleteFlg 削除フラグ
 */
data class Author(
    // 登録・更新処理を考慮し、各項目をnull許容とした(nullの場合は更新対象外)
    val id: Int?,
    val authorName: String?,
    val birthday: LocalDate?,
    // CreateBy、UpdateByの登録・更新用の値
    val operator: String?,
    // deleteFlgの登録・更新用の値
    val deleteFlg: String?,
)
