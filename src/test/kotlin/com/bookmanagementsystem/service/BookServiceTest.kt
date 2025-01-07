package com.bookmanagementsystem.service

import com.bookmanagementsystem.entity.Book
import com.bookmanagementsystem.enumkt.PublicationStatus
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


class BookServiceTest {

    @Mock
    var bookService: BookService? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("書籍取得処理の正常系_返り値あり")
    fun testGetBook_Success() {
        try {
            val mockBook = Book(
                id = 1,
                authorIdList = listOf(10, 11, 12),
                title = "title",
                price = 9999.0,
                publicationStatus = PublicationStatus.UNPUBLISHED,
                operator = "operator",
                deleteFlg = "0",
            )
            `when`(bookService?.getBook(1)).thenReturn(mockBook)
            // テスト対象メソッドの呼び出し
            val result = bookService?.getBook(1)
            assertNotNull(result)
            assertEquals(1, result?.id)
            assertEquals(listOf(10, 11, 12), result?.authorIdList)
            assertEquals("title", result?.title)
            assertEquals(9999.0, result?.price)
            assertEquals(PublicationStatus.UNPUBLISHED, result?.publicationStatus)
            assertEquals("operator", result?.operator)
            assertEquals("0", result?.deleteFlg)
            // モックメソッドの呼び出しを検証
            verify(bookService)?.getBook(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("書籍取得処理の正常系_返り値なし")
    fun testGetBook_Success_NotExist() {
        try {
            `when`(bookService?.getBook(1)).thenReturn(null)
            // テスト対象メソッドの呼び出し
            val result = bookService?.getBook(1)
            assertNull(result)
            // モックメソッドの呼び出しを検証
            verify(bookService)?.getBook(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("書籍登録処理の正常系")
    fun testCreateBook_Success() {
        try {
            val request = Book(
                id = 1,
                authorIdList = listOf(10, 11, 12),
                title = "title",
                price = 9999.0,
                publicationStatus = PublicationStatus.UNPUBLISHED,
                operator = "operator",
                deleteFlg = "0",
            )
            `when`(bookService?.createBook(request)).thenReturn(1)
            val result = bookService!!.createBook(request)
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(bookService)?.createBook(request)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }


    @Test
    @DisplayName("書籍登録処理の異常系")
    fun testCreateBook_IllegalStateException() {
        val request = Book(
            id = 1,
            authorIdList = listOf(10, 11, 12),
            title = "title",
            price = 9999.0,
            publicationStatus = PublicationStatus.UNPUBLISHED,
            operator = "operator",
            deleteFlg = "0",
        )
        `when`(bookService?.createBook(request)).thenThrow(IllegalStateException())
        assertThrows<IllegalStateException> { bookService!!.createBook(request) }
    }

    @Test
    @DisplayName("書籍更新処理の正常系")
    fun testUpdateBook_Success() {
        try {
            val request = Book(
                id = 1,
                authorIdList = listOf(10, 11, 12),
                title = "title",
                price = 9999.0,
                publicationStatus = PublicationStatus.UNPUBLISHED,
                operator = "operator",
                deleteFlg = "0",
            )
            `when`(bookService?.updateBook(request)).thenReturn(1)
            val result = bookService!!.updateBook(request)
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(bookService)?.updateBook(request)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }


    @Test
    @DisplayName("書籍更新処理の異常系")
    fun testUpdateBook_IllegalStateException() {
        val request = Book(
            id = 1,
            authorIdList = listOf(10, 11, 12),
            title = "title",
            price = 9999.0,
            publicationStatus = PublicationStatus.UNPUBLISHED,
            operator = "operator",
            deleteFlg = "0",
        )
        `when`(bookService?.updateBook(request)).thenThrow(IllegalStateException())
        assertThrows<IllegalStateException> { bookService!!.updateBook(request) }
    }
}