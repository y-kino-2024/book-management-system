package com.bookmanagementsystem.request.book

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UpdateBookRequestTest {

    // 正常系のUpdateBookRequest
    val updateBookRequest = UpdateBookRequest(
        bookId = 0,
        authorIdList = listOf(1, 5, 9, 13, 19),
        title = "title",
        price = 777.0,
        publicationStatus = "1",
        operator = "operator",
        deleteFlg = "0"
    )

    @Test
    @DisplayName("UpdateBookRequestのbookIdに設定した値が入っていること")
    fun testGetBookId() {
        val bookId = updateBookRequest.bookId
        Assertions.assertEquals(0, bookId)
    }

    @Test
    @DisplayName("UpdateBookRequestのauthorIdListに設定した値が入っていること")
    fun testGetAuthorIdList() {
        val authorIdList = updateBookRequest.authorIdList
        Assertions.assertEquals(listOf(1, 5, 9, 13, 19), authorIdList)
    }

    @Test
    @DisplayName("UpdateBookRequestのtitleに設定した値が入っていること")
    fun testGetTitle() {
        val title = updateBookRequest.title
        Assertions.assertEquals("title", title)
    }

    @Test
    @DisplayName("UpdateBookRequestのpriceに設定した値が入っていること")
    fun testGetPrice() {
        val price = updateBookRequest.price
        Assertions.assertEquals(777.0, price)
    }

    @Test
    @DisplayName("UpdateBookRequestのpublicationStatusに設定した値が入っていること")
    fun testGetPublicationStatus() {
        val publicationStatus = updateBookRequest.publicationStatus
        Assertions.assertEquals("1", publicationStatus)
    }

    @Test
    @DisplayName("UpdateBookRequestのoperatorに設定した値が入っていること")
    fun testGetOperator() {
        val operator = updateBookRequest.operator
        Assertions.assertEquals("operator", operator)
    }

    @Test
    @DisplayName("UpdateBookRequestのdeleteFlgに設定した値が入っていること")
    fun testGetDeleteFlg() {
        val deleteFlg = updateBookRequest.deleteFlg
        Assertions.assertEquals("0", deleteFlg)
    }
}
