package com.bookmanagementsystem.request.author

import java.time.LocalDate

/**
 * 著者登録処理のリクエストオブジェクト
 * @param authorName 著者名
 * @param birthday 誕生日
 * @param operator 操作者
 */
data class CreateAuthorRequest(
    val authorName: String?,
    val birthday: LocalDate?,
    val operator: String?
)
