package com.bookmanagementsystem.request.author

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UpdateAuthorRequestTest {

    // 正常系のUpdateAuthorRequest
    private val updateAuthorRequest = UpdateAuthorRequest(
        authorId = "1",
        authorName = "authorName",
        birthday = "1988-04-20",
        operator = "operator",
        deleteFlg = "0"
    )

    @Test
    @DisplayName("UpdateAuthorRequestのauthorIdに設定した値が入っていること")
    fun testGetAuthorId() {
        val authorId = updateAuthorRequest.authorId
        Assertions.assertEquals("1", authorId)
    }

    @Test
    @DisplayName("UpdateAuthorRequestのauthorNameに設定した値が入っていること")
    fun testGetAuthorName() {
        val authorName = updateAuthorRequest.authorName
        Assertions.assertEquals("authorName", authorName)
    }

    @Test
    @DisplayName("UpdateAuthorRequestのbirthdayに設定した値が入っていること")
    fun testGetBirthday() {
        val birthday = updateAuthorRequest.birthday
        Assertions.assertEquals("1988-04-20", birthday)
    }

    @Test
    @DisplayName("UpdateAuthorRequestのoperatorに設定した値が入っていること")
    fun testGetOperator() {
        val operator = updateAuthorRequest.operator
        Assertions.assertEquals("operator", operator)
    }

    @Test
    @DisplayName("UpdateAuthorRequestのdeleteFlgに設定した値が入っていること")
    fun testGetDeleteFlg() {
        val deleteFlg = updateAuthorRequest.deleteFlg
        Assertions.assertEquals("0", deleteFlg)
    }
}
