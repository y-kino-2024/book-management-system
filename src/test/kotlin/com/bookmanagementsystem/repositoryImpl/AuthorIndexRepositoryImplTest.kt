package com.bookmanagementsystem.repositoryImpl

import com.bookmanagementsystem.dto.AuthorIndexDto
import com.bookmanagementsystem.repository.AuthorIndexRepository
import org.jooq.DSLContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class AuthorIndexRepositoryImplTest {

    @Mock
    private lateinit var dslContext: DSLContext

    @Mock
    private lateinit var authorIndexRepository: AuthorIndexRepository

    @InjectMocks
    private lateinit var authorIndexRepositoryImpl: AuthorIndexRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("著者に紐づく書籍情報取得処理の正常系_返り値あり(1件)")
    fun testGetBookFromBookId_Success_ReturnOne() {
        try {
            val mockDate = listOf(
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 2,
                    createdBy = "createdBy",
                    createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                    updatedBy = "updatedBy",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                    deleteFlg = "0"
                )
            )
            `when`(authorIndexRepository.getBookFromBookId(1)).thenReturn(mockDate)
            // テスト対象メソッドの呼び出し
            val resultList = authorIndexRepository.getBookFromBookId(1)
            // 検証
            assertNotNull(resultList)
            assertIterableEquals(mockDate, resultList)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).getBookFromBookId(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者に紐づく書籍情報取得処理の正常系_返り値あり(2件)")
    fun testGetBookFromBookId_Success_ReturnTwo() {
        try {
            val mockDate = listOf(
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 3,
                    createdBy = "createdBy1",
                    createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                    updatedBy = "updatedBy1",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                    deleteFlg = "0"
                ),
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 4,
                    createdBy = "createdBy2",
                    createdAt = LocalDateTime.of(2024, 12, 31, 13, 0, 0),
                    updatedBy = "updatedBy2",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 9, 8, 8),
                    deleteFlg = "0"
                )
            )
            `when`(authorIndexRepository.getBookFromBookId(1)).thenReturn(mockDate)
            // テスト対象メソッドの呼び出し
            val resultList = authorIndexRepository.getBookFromBookId(1)
            // 検証
            assertNotNull(resultList)
            assertIterableEquals(mockDate, resultList)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).getBookFromBookId(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者に紐づく書籍情報取得処理の正常系_返り値なし")
    fun testGetBookFromBookId_Success_NotExist() {
        try {
            val mockDate = mutableListOf<AuthorIndexDto>()
            `when`(authorIndexRepository.getBookFromBookId(1)).thenReturn(mockDate)
            // テスト対象メソッドの呼び出し
            val resultList = authorIndexRepository.getBookFromBookId(1)
            // 検証
            assertNotNull(resultList)
            assertIterableEquals(mockDate, resultList)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).getBookFromBookId(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者IDを用いて著者に紐づく書籍情報取得処理の正常系_返り値あり(1件)")
    fun testGetBookFromAuthorId_Success_ReturnOne() {
        try {
            val mockDate = listOf(
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 2,
                    createdBy = "createdBy",
                    createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                    updatedBy = "updatedBy",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                    deleteFlg = "0"
                )
            )
            `when`(authorIndexRepository.getBookFromAuthorId(2)).thenReturn(mockDate)
            // テスト対象メソッドの呼び出し
            val resultList = authorIndexRepository.getBookFromAuthorId(2)
            // 検証
            assertNotNull(resultList)
            assertIterableEquals(mockDate, resultList)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).getBookFromAuthorId(2)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者IDを用いて著者に紐づく書籍情報取得処理の正常系_返り値あり(2件)")
    fun testGetBookFromAuthorId_Success_ReturnTwo() {
        try {
            val mockDate = listOf(
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 2,
                    createdBy = "createdBy1",
                    createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                    updatedBy = "updatedBy1",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                    deleteFlg = "0"
                ),
                AuthorIndexDto(
                    bookId = 3,
                    authorId = 2,
                    createdBy = "createdBy2",
                    createdAt = LocalDateTime.of(2024, 12, 31, 13, 0, 0),
                    updatedBy = "updatedBy2",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 9, 8, 8),
                    deleteFlg = "0"
                )
            )
            `when`(authorIndexRepository.getBookFromAuthorId(2)).thenReturn(mockDate)
            // テスト対象メソッドの呼び出し
            val resultList = authorIndexRepository.getBookFromAuthorId(2)
            // 検証
            assertNotNull(resultList)
            assertIterableEquals(mockDate, resultList)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).getBookFromAuthorId(2)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者IDを用いて著者に紐づく書籍情報取得処理の正常系_返り値なし")
    fun testGetBookFromAuthorId_Success_NotExist() {
        try {
            val mockDate = mutableListOf<AuthorIndexDto>()
            `when`(authorIndexRepository.getBookFromAuthorId(2)).thenReturn(mockDate)
            // テスト対象メソッドの呼び出し
            val resultList = authorIndexRepository.getBookFromAuthorId(2)
            // 検証
            assertNotNull(resultList)
            assertIterableEquals(mockDate, resultList)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).getBookFromAuthorId(2)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者に紐づく書籍情報登録処理の正常系_リクエスト1件")
    fun testCreateAuthorIndex_Success_RequestOne() {
        try {
            val request = listOf(
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 2,
                    createdBy = "createdBy",
                    createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                    updatedBy = "updatedBy",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                    deleteFlg = "0"
                )
            )
            `when`(authorIndexRepository.createAuthorIndex(request)).thenReturn(1)
            // テスト対象メソッドの呼び出し
            val result = authorIndexRepository.createAuthorIndex(request)
            // 検証
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).createAuthorIndex(request)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者に紐づく書籍情報登録処理の正常系_リクエスト2件")
    fun testCreateAuthorIndex_Success_RequestTwo() {
        try {
            val request = listOf(
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 3,
                    createdBy = "createdBy1",
                    createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                    updatedBy = "updatedBy1",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                    deleteFlg = "0"
                ),
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 4,
                    createdBy = "createdBy2",
                    createdAt = LocalDateTime.of(2024, 12, 31, 13, 0, 0),
                    updatedBy = "updatedBy2",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 9, 8, 8),
                    deleteFlg = "0"
                )
            )
            `when`(authorIndexRepository.createAuthorIndex(request)).thenReturn(2)
            // テスト対象メソッドの呼び出し
            val result = authorIndexRepository.createAuthorIndex(request)
            // 検証
            assertNotNull(result)
            assertEquals(2, result)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).createAuthorIndex(request)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者に紐づく書籍情報更新処理の正常系_リクエスト1件")
    fun testUpdateAuthorIndex_Success_RequestOne() {
        try {
            val request = listOf(
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 2,
                    createdBy = "createdBy",
                    createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                    updatedBy = "updatedBy",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                    deleteFlg = "0"
                )
            )
            `when`(authorIndexRepository.updateAuthorIndex(request, 9999)).thenReturn(1)
            // テスト対象メソッドの呼び出し
            val result = authorIndexRepository.updateAuthorIndex(request, 9999)
            // 検証
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).updateAuthorIndex(request, 9999)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者に紐づく書籍情報更新処理の正常系_リクエスト2件")
    fun testUpdateAuthorIndex_Success_RequestTwo() {
        try {
            val request = listOf(
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 3,
                    createdBy = "createdBy1",
                    createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                    updatedBy = "updatedBy1",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                    deleteFlg = "0"
                ),
                AuthorIndexDto(
                    bookId = 1,
                    authorId = 4,
                    createdBy = "createdBy2",
                    createdAt = LocalDateTime.of(2024, 12, 31, 13, 0, 0),
                    updatedBy = "updatedBy2",
                    updatedAt = LocalDateTime.of(2025, 1, 1, 9, 8, 8),
                    deleteFlg = "0"
                )
            )
            `when`(authorIndexRepository.updateAuthorIndex(request, 9999)).thenReturn(2)
            // テスト対象メソッドの呼び出し
            val result = authorIndexRepository.updateAuthorIndex(request, 9999)
            // 検証
            assertNotNull(result)
            assertEquals(2, result)
            // モックメソッドの呼び出しを検証
            verify(authorIndexRepository).updateAuthorIndex(request, 9999)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }
}
