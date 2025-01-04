package com.bookmanagementsystem.repository

import com.bookmanagementsystem.dto.BooksInfoDto
import org.springframework.stereotype.Repository

/**
 * Books_infoのリポジトリクラス
 */
@Repository
interface BooksInfoRepository {

    fun fetchBook(bookId: Int): BooksInfoDto?

    fun createBook(bookDto: BooksInfoDto): Int?

    fun updateBook(bookDto: BooksInfoDto): Int
}
