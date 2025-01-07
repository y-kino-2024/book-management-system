package com.bookmanagementsystem.response.book

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UpdateBookResponseTest {

    // CreateBookResponse
    private val updateBookResponse: UpdateBookResponse = UpdateBookResponse(
        bookId = 0,
    )

    @Test
    @DisplayName("UpdateBookResponseのbookIdに設定した値が入っていること")
    fun testGetBookId() {
        val bookId = updateBookResponse.bookId
        Assertions.assertEquals(0, bookId)
    }
}
