package com.bookmanagementsystem.response.author

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UpdateAuthorResponseTest {

    // 正常系のUpdateAuthorResponse
    private val updateAuthorResponse = UpdateAuthorResponse(
        authorId = 0,
    )

    @Test
    @DisplayName("UpdateAuthorResponseのauthorIdに設定した値が入っていること")
    fun testGetAuthorId() {
        val authorId = updateAuthorResponse.authorId
        Assertions.assertEquals(0, authorId)
    }
}
