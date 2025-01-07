package com.bookmanagementsystem.repositoryImpl

import com.bookmanagementsystem.dto.AuthorsInfoDto
import com.bookmanagementsystem.repository.AuthorsInfoRepository
import org.jooq.DSLContext
import org.junit.jupiter.api.Assertions.*
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
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
@Transactional
class AuthorsInfoRepositoryImplTest {

    @Mock
    private lateinit var dslContext: DSLContext

    @Mock
    private lateinit var authorsInfoRepository: AuthorsInfoRepository

    @InjectMocks
    private lateinit var authorsInfoRepositoryImpl: AuthorsInfoRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("著者取得処理の正常系_返り値あり")
    fun testFetchAuthor_Success_Return() {
        try {
            val mockDate = AuthorsInfoDto(
                id = 1,
                authorName = "authorName",
                birthday = LocalDate.of(1988, 4, 20),
                createdBy = "createdBy",
                createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                updatedBy = "updatedBy",
                updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                deleteFlg = "0"
            )
            `when`(authorsInfoRepository.fetchAuthor(1)).thenReturn(mockDate)

            // テスト対象メソッドの呼び出し
            val result = authorsInfoRepository.fetchAuthor(1)

            // 検証
            assertNotNull(result)
            assertEquals(1, result?.id)
            assertEquals("authorName", result?.authorName)
            assertEquals(LocalDate.of(1988, 4, 20), result?.birthday)
            assertEquals("createdBy", result?.createdBy)
            assertEquals(LocalDateTime.of(2024, 12, 31, 12, 0, 0), result?.createdAt)
            assertEquals("updatedBy", result?.updatedBy)
            assertEquals(LocalDateTime.of(2025, 1, 1, 8, 8, 8), result?.updatedAt)
            assertEquals("0", result?.deleteFlg)

            // モックメソッドの呼び出しを検証
            verify(authorsInfoRepository).fetchAuthor(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者取得処理の正常系_返り値なし")
    fun testFetchAuthor_Success_NotExist() {
        try {
            val mockDate = null
            `when`(authorsInfoRepository.fetchAuthor(1)).thenReturn(mockDate)

            // テスト対象メソッドの呼び出し
            val result = authorsInfoRepository.fetchAuthor(1)

            // 検証
            assertNull(result)

            // モックメソッドの呼び出しを検証
            verify(authorsInfoRepository).fetchAuthor(1)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    /*
        @Test
        @DisplayName("著者取得処理の異常系")
        fun testFetchAuthor_SQLException() {
            try {
                val mockDate = null
                `when`(authorsInfoRepositoryImpl.fetchAuthor(1)).thenThrow(SQLException())
                authorsInfoRepositoryImpl.fetchAuthor(1)
                fail("例外がthrowされませんでした")
            } catch (se: SQLException) {
                assertEquals("DB処理実施時にエラーが発生しました。", se.message)
            } catch (e: Exception) {
                fail("想定した例外がthrowされませんでした"+ e)
            }
        }
    */
    @Test
    @DisplayName("著者登録処理の正常系_返り値あり")
    fun testCreateAuthor_Success_Return() {
        try {
            val mockDate = AuthorsInfoDto(
                id = 1,
                authorName = "authorName",
                birthday = LocalDate.of(1988, 4, 20),
                createdBy = "createdBy",
                createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                updatedBy = "updatedBy",
                updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                deleteFlg = "0"
            )
            `when`(authorsInfoRepository.createAuthor(mockDate)).thenReturn(1)

            // テスト対象メソッドの呼び出し
            val result = authorsInfoRepository.createAuthor(mockDate)

            // 検証
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(authorsInfoRepository).createAuthor(mockDate)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    fun testUpdateAuthor() {
        try {
            val mockDate = AuthorsInfoDto(
                id = 1,
                authorName = "authorName",
                birthday = LocalDate.of(1988, 4, 20),
                createdBy = "createdBy",
                createdAt = LocalDateTime.of(2024, 12, 31, 12, 0, 0),
                updatedBy = "updatedBy",
                updatedAt = LocalDateTime.of(2025, 1, 1, 8, 8, 8),
                deleteFlg = "0"
            )
            `when`(authorsInfoRepository.updateAuthor(mockDate)).thenReturn(1)

            // テスト対象メソッドの呼び出し
            val result = authorsInfoRepository.updateAuthor(mockDate)

            // 検証
            assertNotNull(result)
            assertEquals(1, result)
            // モックメソッドの呼び出しを検証
            verify(authorsInfoRepository).updateAuthor(mockDate)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }
}