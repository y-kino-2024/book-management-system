package com.bookmanagementsystem.repository

import com.bookmanagementsystem.dto.AuthorIndexDto
import org.springframework.stereotype.Repository

/**
 * author_indexのリポジトリクラス
 */
@Repository
interface AuthorIndexRepository {

    fun getBookFromBookId(bookId:Int): List<AuthorIndexDto>?

    fun getBookFromAuthorId(authorId: Int): List<AuthorIndexDto>?

    fun createBookFromAuthor(authorIndexDto: List<AuthorIndexDto>): Int

    fun updateBookFromAuthor(authorIndexDto: AuthorIndexDto): Int
}
