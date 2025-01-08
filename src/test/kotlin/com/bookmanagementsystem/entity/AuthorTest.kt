package com.bookmanagementsystem.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class AuthorTest {

    val mockAuthor = Author(
        id = 0,
        authorName = "authorName",
        birthday = LocalDate.of(1988, 4, 20),
        operator = "operator",
        deleteFlg = "0",
    )

    @Test
    @DisplayName("Authorのidに設定した値が入っていること")
    fun testGetId() {
        val id = mockAuthor.id
        assertEquals(0, id)
    }

    @Test
    @DisplayName("AuthorのauthorNameに設定した値が入っていること")
    fun testGetAuthorName() {
        val authorName = mockAuthor.authorName
        assertEquals("authorName", authorName)
    }

    @Test
    @DisplayName("Authorのbirthdayに設定した値が入っていること")
    fun testGetBirthday() {
        val birthday = mockAuthor.birthday
        assertEquals(LocalDate.of(1988, 4, 20), birthday)
    }

    @Test
    @DisplayName("Authorのoperatorに設定した値が入っていること")
    fun testGetOperator() {
        val operator = mockAuthor.operator
        assertEquals("operator", operator)
    }

    @Test
    @DisplayName("AuthorのdeleteFlgに設定した値が入っていること")
    fun testGetDeleteFlg() {
        val deleteFlg = mockAuthor.deleteFlg
        assertEquals("0", deleteFlg)
    }
}
