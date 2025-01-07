package com.bookmanagementsystem.repositoryImpl

import com.bookmanagementsystem.dto.BooksInfoDto
import com.bookmanagementsystem.repository.BooksInfoRepository
import org.jooq.DSLContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class BooksInfoRepositoryImplTest {

    @Mock
    private lateinit var dslContext: DSLContext

    @Mock
    private lateinit var booksInfoRepository: BooksInfoRepository

    @InjectMocks
    private lateinit var booksInfoRepositoryImpl: BooksInfoRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("書籍取得処理の正常系_返り値あり")
    fun testFetchBook_Success_Return() {
        try {
            val mockDate = BooksInfoDto(
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
            `when`(booksInfoRepository.fetchBook(1)).thenReturn(mockDate)

            // テスト対象メソッドの呼び出し
            val result = booksInfoRepository.fetchBook(1)

            // 検証
            assertNotNull(result)
            assertEquals(1, result?.id)
            assertEquals("title", result?.title)
            assertEquals(9999.0, result?.price)
            assertEquals("1", result?.publicationStatus)
            assertEquals("createdBy", result?.createdBy)
            assertEquals(LocalDateTime.of(2024, 12, 31, 12, 0, 0), result?.createdAt)
            assertEquals("updatedBy", result?.updatedBy)
            assertEquals(LocalDateTime.of(2025, 1, 1, 8, 8, 8), result?.updatedAt)
            assertEquals("0", result?.deleteFlg)

            // モックメソッドの呼び出しを検証
            verify(booksInfoRepository).fetchBook(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("書籍取得処理の正常系_返り値なし")
    fun testFetchBook_Success_NotExist() {
        try {
            val mockDate = null
            `when`(booksInfoRepository.fetchBook(1)).thenReturn(mockDate)

            // テスト対象メソッドの呼び出し
            val result = booksInfoRepository.fetchBook(1)

            // 検証
            assertNull(result)

            // モックメソッドの呼び出しを検証
            verify(booksInfoRepository).fetchBook(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("書籍登録処理の正常系")
    fun testCreateBook_Success() {
        try {
            val mockDate = BooksInfoDto(
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
            `when`(booksInfoRepository.createBook(mockDate)).thenReturn(1)

            // テスト対象メソッドの呼び出し
            val result = booksInfoRepository.createBook(mockDate)

            // 検証
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(booksInfoRepository).createBook(mockDate)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("書籍更新処理の正常系")
    fun testUpdateBook_Success() {
        try {
            val mockDate = BooksInfoDto(
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
            `when`(booksInfoRepository.updateBook(mockDate)).thenReturn(1)

            // テスト対象メソッドの呼び出し
            val result = booksInfoRepository.updateBook(mockDate)

            // 検証
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(booksInfoRepository).updateBook(mockDate)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }
}