package com.bookmanagementsystem.request.author

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class CreateAuthorRequestTest {

    // 正常系のCreateAuthorRequest
    private val createAuthorRequest = CreateAuthorRequest(
        authorName = "authorName",
        birthday = "1988-04-20",
        operator = "operator"
    )

    @Test
    @DisplayName("CreateAuthorRequestのauthorNameに設定した値が入っていること")
    fun testGetAuthorName() {
        val authorName = createAuthorRequest.authorName
        Assertions.assertEquals("authorName", authorName)
    }

    @Test
    @DisplayName("CreateAuthorRequestのbirthdayに設定した値が入っていること")
    fun testGetBirthday() {
        val birthday = createAuthorRequest.birthday
        Assertions.assertEquals("1988-04-20", birthday)
    }

    @Test
    @DisplayName("CreateAuthorRequestのoperatorに設定した値が入っていること")
    fun testGetOperator() {
        val operator = createAuthorRequest.operator
        Assertions.assertEquals("operator", operator)
    }
}
