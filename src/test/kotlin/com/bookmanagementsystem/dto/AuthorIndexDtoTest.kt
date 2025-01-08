package com.bookmanagementsystem.dto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class AuthorIndexDtoTest {

    val mockAuthorIndexDto = AuthorIndexDto(
        bookId = 1,
        authorId = 2,
        createdBy = "createdBy",
        createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
        updatedBy = "updatedBy",
        updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
        deleteFlg = "0"
    )

    @Test
    @DisplayName("AuthorIndexDtoのbookIdに設定した値が入っていること")
    fun testGetBookId() {
        val bookId = mockAuthorIndexDto.bookId
        Assertions.assertEquals(1, bookId)
    }

    @Test
    @DisplayName("AuthorIndexDtoのauthorIdに設定した値が入っていること")
    fun testGetAuthorId() {
        val authorId = mockAuthorIndexDto.authorId
        Assertions.assertEquals(2, authorId)
    }

    @Test
    @DisplayName("AuthorIndexDtoのCreatedByに設定した値が入っていること")
    fun testGetCreatedBy() {
        val createdBy = mockAuthorIndexDto.createdBy
        Assertions.assertEquals("createdBy", createdBy)
    }

    @Test
    @DisplayName("AuthorIndexDtoのcreatedAtに設定した値が入っていること")
    fun testGetCreatedAt() {
        val createdAt = mockAuthorIndexDto.createdAt
        Assertions.assertEquals(LocalDateTime.of(2024, 12, 31, 12, 0, 0), createdAt)
    }

    @Test
    @DisplayName("AuthorIndexDtoのupdatedByに設定した値が入っていること")
    fun testGetUpdatedBy() {
        val updatedBy = mockAuthorIndexDto.updatedBy
        Assertions.assertEquals("updatedBy", updatedBy)
    }

    @Test
    @DisplayName("AuthorIndexDtoのupdatedAtに設定した値が入っていること")
    fun testGetUpdatedAt() {
        val updatedAt = mockAuthorIndexDto.updatedAt
        Assertions.assertEquals(LocalDateTime.of(2025, 1, 1, 8, 8, 8), updatedAt)
    }

    @Test
    @DisplayName("AuthorIndexDtoのdeleteFlgに設定した値が入っていること")
    fun testGetDeleteFlg() {
        val deleteFlg = mockAuthorIndexDto.deleteFlg
        Assertions.assertEquals("0", deleteFlg)
    }
}
