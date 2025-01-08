package com.bookmanagementsystem.request.book

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetBookFromAuthorRequestTest {

    // 正常系のGetBookRequest
    val getBookFromAuthorRequest = GetBookFromAuthorRequest(
        authorId = 0,
    )

    @Test
    @DisplayName("GetBookFromAuthorRequestのauthorIdに設定した値が入っていること")
    fun testGetAuthorId() {
        val authorId = getBookFromAuthorRequest.authorId
        Assertions.assertEquals(0, authorId)
    }
}
