package com.bookmanagementsystem.request.book

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreateBookRequestTest {

    // 正常系のCreateBookRequest
    val createBookRequest = CreateBookRequest(
        authorIdList = listOf(1, 5, 9, 13, 19),
        title = "title",
        price = 777.0,
        publicationStatus = "1",
        operator = "operator",
    )

    @Test
    @DisplayName("CreateBookRequestのauthorIdListに設定した値が入っていること")
    fun testGetAuthorIdList() {
        val authorIdList = createBookRequest.authorIdList
        Assertions.assertEquals(listOf(1, 5, 9, 13, 19), authorIdList)
    }

    @Test
    @DisplayName("CreateBookRequestのtitleに設定した値が入っていること")
    fun testGetTitle() {
        val title = createBookRequest.title
        Assertions.assertEquals("title", title)
    }

    @Test
    @DisplayName("CreateBookRequestのpriceに設定した値が入っていること")
    fun testGetPrice() {
        val price = createBookRequest.price
        Assertions.assertEquals(777.0, price)
    }

    @Test
    @DisplayName("CreateBookRequestのpublicationStatusに設定した値が入っていること")
    fun testGetPublicationStatus() {
        val publicationStatus = createBookRequest.publicationStatus
        Assertions.assertEquals("1", publicationStatus)
    }

    @Test
    @DisplayName("CreateBookRequestのoperatorに設定した値が入っていること")
    fun testGetOperator() {
        val operator = createBookRequest.operator
        Assertions.assertEquals("operator", operator)
    }
}
