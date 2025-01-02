package com.bookmanagementsystem.request.author

import java.time.LocalDate

/**
 * 著者更新処理のリクエストオブジェクト
 * @param authorId 著者ID
 * @param authorName 著者名
 * @param birthday 誕生日
 * @param operator 操作者
 */
data class UpdateAuthorRequest(
    val authorId: String?,
    val authorName: String?,
    val birthday: LocalDate?,
    val operator: String?
)
