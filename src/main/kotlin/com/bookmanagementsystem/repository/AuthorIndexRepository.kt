package com.bookmanagementsystem.repository

import com.bookmanagementsystem.dto.AuthorIndexDto
import org.springframework.stereotype.Repository

/**
 * author_indexのリポジトリクラス
 */
@Repository
interface AuthorIndexRepository {

    fun getBookFromBookId(bookId: Int): List<AuthorIndexDto>?

    fun getBookFromAuthorId(authorId: Int): List<AuthorIndexDto>?

    fun createAuthorIndex(authorIndexDtoList: List<AuthorIndexDto>): Int

    fun updateAuthorIndex(authorIndexDtoList: List<AuthorIndexDto>, bookId: Int): Int
}
