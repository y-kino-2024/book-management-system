package com.bookmanagementsystem.repository

import com.bookmanagementsystem.dto.AuthorIndexDto
import org.springframework.stereotype.Repository

/**
 * author_indexのリポジトリクラス
 */
@Repository
interface AuthorIndexRepository {

    fun getAuthorIndexFromBookId(bookId: Int): List<AuthorIndexDto>?

    fun getAuthorIndexFromAuthorId(authorId: Int): List<AuthorIndexDto>?

    fun createAuthorIndex(authorIndexDtoList: List<AuthorIndexDto>): Int

    fun updateAuthorIndex(authorIndexDtoList: List<AuthorIndexDto>, bookId: Int): Int
}
