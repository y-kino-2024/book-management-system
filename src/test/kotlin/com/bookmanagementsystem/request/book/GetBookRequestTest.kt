package com.bookmanagementsystem.request.book

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetBookRequestTest {

    // 正常系のGetBookRequest
    val getBookRequest = GetBookRequest(
        bookId = "1",
    )

    @Test
    @DisplayName("GetBookRequestのbookIdに設定した値が入っていること")
    fun testGetBookId() {
        val bookId = getBookRequest.bookId
        Assertions.assertEquals("1", bookId)
    }
}
