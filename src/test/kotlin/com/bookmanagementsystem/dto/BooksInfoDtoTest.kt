package com.bookmanagementsystem.dto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class BooksInfoDtoTest {

    val mockBooksInfoDto = BooksInfoDto(
        id = 1,
        title = "title",
        price = 9999.0,
        publicationStatus = "1",
        createdBy = "createdBy",
        createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
        updatedBy = "updatedBy",
        updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
        deleteFlg = "0"
    )

    @Test
    @DisplayName("BooksInfoDtoのidに設定した値が入っていること")
    fun testGetId() {
        val id = mockBooksInfoDto.id
        Assertions.assertEquals(1, id)
    }

    @Test
    @DisplayName("BooksInfoDtoのtitleに設定した値が入っていること")
    fun testGetTitle() {
        val title = mockBooksInfoDto.title
        Assertions.assertEquals("title", title)
    }

    @Test
    @DisplayName("BooksInfoDtoのpriceに設定した値が入っていること")
    fun testGetPrice() {
        val price = mockBooksInfoDto.price
        Assertions.assertEquals(9999.0, price)
    }

    @Test
    @DisplayName("BooksInfoDtoのpublicationStatusに設定した値が入っていること")
    fun testGetPublicationStatus() {
        val publicationStatus = mockBooksInfoDto.publicationStatus
        Assertions.assertEquals("1", publicationStatus)
    }

    @Test
    @DisplayName("BooksInfoDtoのcreatedByに設定した値が入っていること")
    fun testGetCreatedBy() {
        val createdBy = mockBooksInfoDto.createdBy
        Assertions.assertEquals("createdBy", createdBy)
    }

    @Test
    @DisplayName("BooksInfoDtoのcreatedAtに設定した値が入っていること")
    fun testGetCreatedAt() {
        val createdAt = mockBooksInfoDto.createdAt
        Assertions.assertEquals(LocalDateTime.of(2024, 12, 31, 12, 0, 0), createdAt)
    }

    @Test
    @DisplayName("BooksInfoDtoのupdatedByに設定した値が入っていること")
    fun testGetUpdatedBy() {
        val updatedBy = mockBooksInfoDto.updatedBy
        Assertions.assertEquals("updatedBy", updatedBy)
    }

    @Test
    @DisplayName("BooksInfoDtoのupdatedAtに設定した値が入っていること")
    fun testGetUpdatedAt() {
        val updatedAt = mockBooksInfoDto.updatedAt
        Assertions.assertEquals(LocalDateTime.of(2025, 1, 1, 8, 8, 8), updatedAt)
    }

    @Test
    @DisplayName("BooksInfoDtoのdeleteFlgに設定した値が入っていること")
    fun testGetDeleteFlg() {
        val deleteFlg = mockBooksInfoDto.deleteFlg
        Assertions.assertEquals("0", deleteFlg)
    }
}
