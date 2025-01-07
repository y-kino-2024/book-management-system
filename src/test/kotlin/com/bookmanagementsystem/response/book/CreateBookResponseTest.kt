package com.bookmanagementsystem.response.book

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreateBookResponseTest {

    // CreateBookResponse
    private val createBookResponse: CreateBookResponse = CreateBookResponse(
        bookId = 0,
    )

    @Test
    @DisplayName("CreateBookResponseのbookIdに設定した値が入っていること")
    fun testGetBookId() {
        val bookId = createBookResponse.bookId
        Assertions.assertEquals(0, bookId)
    }
}
