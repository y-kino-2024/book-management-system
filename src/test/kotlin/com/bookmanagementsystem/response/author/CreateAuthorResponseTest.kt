package com.bookmanagementsystem.response.author

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreateAuthorResponseTest {

    // 正常系のCreateAuthorResponse
    private val createAuthorResponse = CreateAuthorResponse(
        authorId = 0,
    )

    @Test
    @DisplayName("CreateAuthorResponseのauthorIdに設定した値が入っていること")
    fun testGetAuthorId() {
        val authorId = createAuthorResponse.authorId
        Assertions.assertEquals(0, authorId)
    }
}
