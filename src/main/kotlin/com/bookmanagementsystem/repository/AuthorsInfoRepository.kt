package com.bookmanagementsystem.repository

import com.bookmanagementsystem.dto.AuthorsInfoDto
import org.springframework.stereotype.Repository

/**
 * Authors_infoのリポジトリクラス
 */
@Repository
interface AuthorsInfoRepository {

    fun fetchAuthor(authorId: String): AuthorsInfoDto?

    fun createAuthor(authorDto: AuthorsInfoDto): Int

    fun updateAuthor(authorDto: AuthorsInfoDto): Int

    fun currentAuthorIdSequence(): Int
}
