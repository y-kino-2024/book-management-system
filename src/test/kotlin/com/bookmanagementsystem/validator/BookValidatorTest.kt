package com.bookmanagementsystem.validator

import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import java.time.LocalDate

internal class BookValidatorTest {

    @InjectMocks
    var bookValidator: BookValidator? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("書籍取得処理のバリデーションチェックにて、全ての値が正常値の場合はエラーとならないこと")
    fun testValidGetBook_Success() {
        try {
            bookValidator!!.validGetBook(GetBookRequest(0))
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("書籍取得処理のバリデーションチェックにて、書籍IDがnullの場合はNullPointerExceptionが投げられること")
    fun testValidGetBook_Success_NullPointerException() {
        try {
            bookValidator!!.validGetBook(GetBookRequest(null))
            fail("例外がthrowされませんでした")
        } catch (e: NullPointerException) {
            assertEquals("bookIdを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("書籍登録処理のバリデーションチェックにて、全ての値が正常値の場合はエラーとならないこと")
    fun testValidCreateBook_Success() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = listOf(0,1,2,3,4),
                    title = "title",
                    price = 500.0,
                    publicationStatus = "0",
                    operator = "operator"
                )
            )
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("書籍登録処理のバリデーションチェックにて、著者IDリストがnullの場合はNullPointerExceptionが投げられること")
    fun testValidCreateBook_NullPointerException_AuthorIdList() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = null,
                    title = "title",
                    price = 500.0,
                    publicationStatus = "0",
                    operator = "operator"
                )
            )
        } catch (e: NullPointerException) {
            assertEquals("authorIdListを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("書籍登録処理のバリデーションチェックにて、タイトルがnullの場合はNullPointerExceptionが投げられること")
    fun testValidCreateBook_NullPointerException_Title() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = listOf(0,1,2,3,4),
                    title = null,
                    price = 500.0,
                    publicationStatus = "0",
                    operator = "operator"
                )
            )
        } catch (e: NullPointerException) {
            assertEquals("titleを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("書籍登録処理のバリデーションチェックにて、価格がnullの場合はNullPointerExceptionが投げられること")
    fun testValidCreateBook_NullPointerException_Price() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = listOf(0,1,2,3,4),
                    title = "title",
                    price = null,
                    publicationStatus = "0",
                    operator = "operator"
                )
            )
        } catch (e: NullPointerException) {
            assertEquals("priceを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("書籍登録処理のバリデーションチェックにて、出版状況がnullの場合はNullPointerExceptionが投げられること")
    fun testValidCreateBook_NullPointerException_PublicationStatus() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = listOf(0,1,2,3,4),
                    title = "title",
                    price = 500.0,
                    publicationStatus = null,
                    operator = "operator"
                )
            )
        } catch (e: NullPointerException) {
            assertEquals("publicationStatusを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }


}
