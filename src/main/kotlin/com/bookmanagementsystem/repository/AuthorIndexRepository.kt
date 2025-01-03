package com.bookmanagementsystem.repository

import com.bookmanagementsystem.dto.BooksInfoDto
import org.springframework.stereotype.Repository

/**
 * author_indexのリポジトリクラス
 */
@Repository
interface AuthorIndexRepository {

    fun getBookFromAuthor(authorId: String): BooksInfoDto?
}
