package com.bookmanagementsystem.response.book

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GetBookResponseTest {

    // CreateBookResponse
    private val getBookResponse: GetBookResponse = GetBookResponse(
        bookId = 0,
        authorIdList = listOf(5, 15, 45),
        title = "title",
        price = 999,
        publicationStatus = "1",
    )

    @Test
    @DisplayName("GetBookResponseのbookIdに設定した値が入っていること")
    fun testGetBookId() {
        val bookId = getBookResponse.bookId
        Assertions.assertEquals(0, bookId)
    }

    @Test
    @DisplayName("GetBookResponseのauthorIdListに設定した値が入っていること")
    fun testGetAuthorIdList() {
        val authorIdList = getBookResponse.authorIdList
        Assertions.assertEquals(listOf(5, 15, 45), authorIdList)
    }

    @Test
    @DisplayName("GetBookResponseのtitleに設定した値が入っていること")
    fun testGetTitle() {
        val title = getBookResponse.title
        Assertions.assertEquals("title", title)
    }

    @Test
    @DisplayName("GetBookResponseのpriceに設定した値が入っていること")
    fun testGetPrice() {
        val price = getBookResponse.price
        Assertions.assertEquals(999, price)
    }

    @Test
    @DisplayName("GetBookResponseのpublicationStatusに設定した値が入っていること")
    fun testGetPublicationStatus() {
        val publicationStatus = getBookResponse.publicationStatus
        Assertions.assertEquals("1", publicationStatus)
    }
}
