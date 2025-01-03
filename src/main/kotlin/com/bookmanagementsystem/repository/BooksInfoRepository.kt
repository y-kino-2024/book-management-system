package com.bookmanagementsystem.repository

import com.bookmanagementsystem.dto.BooksInfoDto
import org.springframework.stereotype.Repository

/**
 * Books_infoのリポジトリクラス
 */
@Repository
interface BooksInfoRepository {

    fun getBook(bookId: String): BooksInfoDto?

    fun createBook(book: BooksInfoDto): String

    fun updateBook(book: BooksInfoDto): String
}
