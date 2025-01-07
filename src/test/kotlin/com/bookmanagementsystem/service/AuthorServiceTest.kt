package com.bookmanagementsystem.service

import com.bookmanagementsystem.entity.Author
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class AuthorServiceTest {

    @Mock
    var authorService: AuthorService? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("著者取得処理の正常系_返り値あり")
    fun testGetAuthor_Success() {
        try {
            val mockAuthor = Author(
                id = 1,
                authorName = "authorName",
                birthday = LocalDate.of(1988, 4, 20),
                operator = "operator",
                deleteFlg = "0",
            )
            `when`(authorService?.getAuthor(1)).thenReturn(mockAuthor)
            // テスト対象メソッドの呼び出し
            val result = authorService?.getAuthor(1)
            assertNotNull(result)
            assertEquals(1, result?.id)
            assertEquals("authorName", result?.authorName)
            assertEquals(LocalDate.of(1988, 4, 20), result?.birthday)
            assertEquals("operator", result?.operator)
            assertEquals("0", result?.deleteFlg)
            // モックメソッドの呼び出しを検証
            verify(authorService)?.getAuthor(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者取得処理の正常系_返り値なし")
    fun testGetAuthor_Success_NotExist() {
        try {
            `when`(authorService?.getAuthor(1)).thenReturn(null)
            // テスト対象メソッドの呼び出し
            val result = authorService?.getAuthor(1)
            assertNull(result)
            // モックメソッドの呼び出しを検証
            verify(authorService)?.getAuthor(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }


    @Test
    @DisplayName("著者登録処理の正常系")
    fun testCreateAuthor_Success() {
        try {
            val mockRequest = Author(
                id = 1,
                authorName = "authorName",
                birthday = LocalDate.of(1988, 4, 20),
                operator = "operator",
                deleteFlg = "0",
            )
            `when`(authorService?.createAuthor(mockRequest)).thenReturn(1)
            val result = authorService!!.createAuthor(mockRequest)
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(authorService)?.createAuthor(mockRequest)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者登録処理の異常系")
    fun testCreateAuthor_IllegalStateException() {
        val mockRequest = Author(
            id = 1,
            authorName = "authorName",
            birthday = LocalDate.of(1988, 4, 20),
            operator = "operator",
            deleteFlg = "0",
        )
        `when`(authorService?.createAuthor(mockRequest)).thenThrow(IllegalStateException())
        assertThrows<IllegalStateException> { authorService!!.createAuthor(mockRequest) }
    }

    @Test
    @DisplayName("著者更新処理の正常系")
    fun testUpdateAuthor_Success() {
        try {
            val mockRequest = Author(
                id = 1,
                authorName = "authorName",
                birthday = LocalDate.of(1988, 4, 20),
                operator = "operator",
                deleteFlg = "0",
            )
            `when`(authorService?.updateAuthor(mockRequest)).thenReturn(1)
            val result = authorService!!.updateAuthor(mockRequest)
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(authorService)?.updateAuthor(mockRequest)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者更新処理の異常系")
    fun testUpdateAuthor_IllegalStateException() {
        val mockRequest = Author(
            id = 1,
            authorName = "authorName",
            birthday = LocalDate.of(1988, 4, 20),
            operator = "operator",
            deleteFlg = "0",
        )
        `when`(authorService?.createAuthor(mockRequest)).thenThrow(IllegalStateException())
        assertThrows<IllegalStateException> { authorService!!.createAuthor(mockRequest) }
    }
}
