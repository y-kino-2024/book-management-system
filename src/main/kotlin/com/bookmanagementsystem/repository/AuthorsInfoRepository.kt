package com.bookmanagementsystem.repository

import com.bookmanagementsystem.dto.AuthorsInfoDto
import org.springframework.stereotype.Repository

/**
 * Authors_infoのリポジトリクラス
 */
@Repository
interface AuthorsInfoRepository {

    fun getAuthor(authorId: String): AuthorsInfoDto

    fun createAuthor(author: AuthorsInfoDto): String

    fun updateAuthor(author: AuthorsInfoDto): String
}
