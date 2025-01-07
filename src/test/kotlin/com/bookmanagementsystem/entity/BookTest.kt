package com.bookmanagementsystem.entity

import com.bookmanagementsystem.enumkt.PublicationStatus
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookTest {

    val mockBook: Book = Book(
        id = 0,
        authorIdList = listOf(1, 3, 5, 7, 9),
        title = "title",
        price = 123.0,
        publicationStatus = PublicationStatus.UNPUBLISHED,
        operator = "operator",
        deleteFlg = "0",
    )

    @Test
    @DisplayName("Bookのidに設定した値が入っていること")
    fun testGetId() {
        val id = mockBook.id
        Assertions.assertEquals(0, id)
    }

    @Test
    @DisplayName("BookのauthorIdListに設定した値が入っていること")
    fun testGetAuthorIdList() {
        val authorIdList = mockBook.authorIdList
        Assertions.assertEquals(listOf(1, 3, 5, 7, 9), authorIdList)
    }

    @Test
    @DisplayName("Bookのtitleに設定した値が入っていること")
    fun testGetBirthday() {
        val title = mockBook.title
        Assertions.assertEquals("title", title)
    }

    @Test
    @DisplayName("Bookのpriceに設定した値が入っていること")
    fun testGetPrice() {
        val price = mockBook.price
        Assertions.assertEquals(123.0, price)
    }

    @Test
    @DisplayName("BookのpublicationStatusに設定した値が入っていること")
    fun testGetPublicationStatus() {
        val publicationStatus = mockBook.publicationStatus
        Assertions.assertEquals(PublicationStatus.UNPUBLISHED, publicationStatus)
    }

    @Test
    @DisplayName("Bookのoperatorに設定した値が入っていること")
    fun testGetOperator() {
        val operator = mockBook.operator
        Assertions.assertEquals("operator", operator)
    }

    @Test
    @DisplayName("BookのdeleteFlgに設定した値が入っていること")
    fun testGetDeleteFlg() {
        val deleteFlg = mockBook.deleteFlg
        Assertions.assertEquals("0", deleteFlg)
    }
}
