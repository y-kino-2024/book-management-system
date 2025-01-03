package com.bookmanagementsystem.request.author

import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate

/**
 * 著者登録処理のリクエストオブジェクト
 * @param authorName 著者名
 * @param birthday 誕生日
 * @param operator 操作者
 */
data class CreateAuthorRequest(
    @RequestParam("id")
    val id: Int?,
    @RequestParam("authorName")
    val authorName: String?,
    @RequestParam("birthday")
    val birthday: LocalDate?,
    @RequestParam("operator")
    val operator: String?
)
