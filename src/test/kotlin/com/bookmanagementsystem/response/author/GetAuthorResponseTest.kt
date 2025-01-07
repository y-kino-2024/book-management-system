package com.bookmanagementsystem.response.author

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class GetAuthorResponseTest {

    // 正常系のGetAuthorResponse
    private val getAuthorResponse: GetAuthorResponse = GetAuthorResponse(
        authorId = 0,
        authorName = "0",
        birthday = LocalDate.of(1988, 4, 20)
    )

    @Test
    @DisplayName("GetAuthorResponseのauthorIdに設定した値が入っていること")
    fun testGetAuthorId() {
        val authorId = getAuthorResponse.authorId
        Assertions.assertEquals(0, authorId)
    }

    @Test
    @DisplayName("GetAuthorResponseのauthorNameに設定した値が入っていること")
    fun testGetAuthorName() {
        val authorName = getAuthorResponse.authorName
        Assertions.assertEquals("0", authorName)
    }

    @Test
    @DisplayName("GetAuthorResponseのbirthdayに設定した値が入っていること")
    fun testGetBirthday() {
        val birthday = getAuthorResponse.birthday
        Assertions.assertEquals(LocalDate.of(1988, 4, 20), birthday)
    }
}
