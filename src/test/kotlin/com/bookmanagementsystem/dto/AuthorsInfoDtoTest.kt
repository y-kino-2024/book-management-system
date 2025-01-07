package com.bookmanagementsystem.dto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
class AuthorsInfoDtoTest {

    val mockAuthorsInfoDto: AuthorsInfoDto = AuthorsInfoDto(
        id = 1,
        authorName = "authorName",
        birthday = LocalDate.of(1988, 4, 20),
        createdBy = "createdBy",
        createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
        updatedBy = "updatedBy",
        updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
        deleteFlg = "0"
    )

    @Test
    @DisplayName("AuthorsInfoDtoのidに設定した値が入っていること")
    fun testGetId() {
        val id = mockAuthorsInfoDto.id
        Assertions.assertEquals(1, id)
    }

    @Test
    @DisplayName("AuthorsInfoDtoのauthorNameに設定した値が入っていること")
    fun testGetAuthorName() {
        val authorName = mockAuthorsInfoDto.authorName
        Assertions.assertEquals("authorName", authorName)
    }

    @Test
    @DisplayName("AuthorsInfoDtoのbirthdayに設定した値が入っていること")
    fun testGetBirthday() {
        val birthday = mockAuthorsInfoDto.birthday
        Assertions.assertEquals(LocalDate.of(1988, 4, 20), birthday)
    }

    @Test
    @DisplayName("AuthorsInfoDtoのcreatedByに設定した値が入っていること")
    fun testGetCreatedBy() {
        val createdBy = mockAuthorsInfoDto.createdBy
        Assertions.assertEquals("createdBy", createdBy)
    }

    @Test
    @DisplayName("AuthorsInfoDtoのcreatedAtに設定した値が入っていること")
    fun testGetCreatedAt() {
        val createdAt = mockAuthorsInfoDto.createdAt
        Assertions.assertEquals(LocalDateTime.of(2024, 12, 31, 12, 0, 0), createdAt)
    }

    @Test
    @DisplayName("AuthorsInfoDtoのupdatedByに設定した値が入っていること")
    fun testGetUpdatedBy() {
        val updatedBy = mockAuthorsInfoDto.updatedBy
        Assertions.assertEquals("updatedBy", updatedBy)
    }

    @Test
    @DisplayName("AuthorsInfoDtoのupdatedAtに設定した値が入っていること")
    fun testGetUpdatedAt() {
        val updatedAt = mockAuthorsInfoDto.updatedAt
        Assertions.assertEquals(LocalDateTime.of(2025, 1, 1, 8, 8, 8), updatedAt)
    }

    @Test
    @DisplayName("AuthorsInfoDtoのdeleteFlgに設定した値が入っていること")
    fun testGetDeleteFlg() {
        val deleteFlg = mockAuthorsInfoDto.deleteFlg
        Assertions.assertEquals("0", deleteFlg)
    }
}
