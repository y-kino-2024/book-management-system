package com.bookmanagementsystem.response.author

import java.time.LocalDate

/**
 * 著者取得処理のレスポンスオブジェクト
 *
 * @param authorId 著者ID
 * @param authorName 著者名
 * @param birthday 誕生日
 */
data class GetAuthorResponse(
    val authorId: Int,
    val authorName: String,
    val birthday: LocalDate,
)
