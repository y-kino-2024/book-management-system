package com.bookmanagementsystem.request.author

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetAuthorRequestTest {

    // 正常系のGetAuthorRequest
    private val getAuthorRequest = GetAuthorRequest(
        authorId = "0",
    )

    @Test
    @DisplayName("GetAuthorRequestのauthorIdに設定した値が入っていること")
    fun testGetAuthorId() {
        val authorId = getAuthorRequest.authorId
        Assertions.assertEquals("0", authorId)
    }
}
