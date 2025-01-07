package com.bookmanagementsystem.validator

import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import com.bookmanagementsystem.request.book.UpdateBookRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BookValidatorTest {

    @Mock
    var commonValidator: CommonValidator? = null

    @InjectMocks
    var bookValidator: BookValidator? = null

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
    @DisplayName("書籍取得処理のバリデーションチェックにて、bookIdがnullの場合はNullPointerExceptionが投げられること")
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
                    authorIdList = listOf(0, 1, 2, 3, 4),
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
    @DisplayName("書籍登録処理のバリデーションチェックにて、authorIdListがnullの場合はNullPointerExceptionが投げられること")
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
    @DisplayName("書籍登録処理のバリデーションチェックにて、titleがnullの場合はNullPointerExceptionが投げられること")
    fun testValidCreateBook_NullPointerException_Title() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = listOf(0, 1, 2, 3, 4),
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
    @DisplayName("書籍登録処理のバリデーションチェックにて、priceがnullの場合はNullPointerExceptionが投げられること")
    fun testValidCreateBook_NullPointerException_Price() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = listOf(0, 1, 2, 3, 4),
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
    @DisplayName("書籍登録処理のバリデーションチェックにて、publicationStatusがnullの場合はNullPointerExceptionが投げられること")
    fun testValidCreateBook_NullPointerException_PublicationStatus() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = listOf(0, 1, 2, 3, 4),
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

    @Test
    @DisplayName("書籍登録処理のバリデーションチェックにて、publicationStatusが不正な値の場合はIllegalStateExceptionが投げられること")
    fun testValidCreateBook_IllegalStateException_PublicationStatus() {
        try {
            bookValidator!!.validCreateBook(
                CreateBookRequest(
                    authorIdList = listOf(0, 1, 2, 3, 4),
                    title = "title",
                    price = 500.0,
                    publicationStatus = "2",
                    operator = "operator"
                )
            )
        } catch (e: IllegalStateException) {
            assertEquals("publicationStatusの入力値が不正です。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("書籍更新処理のバリデーションチェックにて、全ての値が正常値の場合はエラーとならないこと")
    fun testValidUpdateBook_Success() {
        try {
            bookValidator!!.validUpdateBook(
                UpdateBookRequest(
                    bookId = 1,
                    authorIdList = listOf(0, 1, 2, 3, 4),
                    title = "title",
                    price = 500.0,
                    publicationStatus = "0",
                    operator = "operator",
                    deleteFlg = "0"
                )
            )
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("書籍更新処理のバリデーションチェックにて、bookIdがnullの場合はNullPointerExceptionが投げられること")
    fun testValidUpdateBook_NullPointerException_BookId() {
        try {
            bookValidator!!.validUpdateBook(
                UpdateBookRequest(
                    bookId = null,
                    authorIdList = listOf(0, 1, 2, 3, 4),
                    title = "title",
                    price = 500.0,
                    publicationStatus = "2",
                    operator = "operator",
                    deleteFlg = "0"
                )
            )
        } catch (e: NullPointerException) {
            assertEquals("bookIdを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("書籍更新処理のバリデーションチェックにて、publicationStatusが不正な値の場合はIllegalStateExceptionが投げられること")
    fun testValidUpdateBook_IllegalStateException_PublicationStatus() {
        try {
            bookValidator!!.validUpdateBook(
                UpdateBookRequest(
                    bookId = 1,
                    authorIdList = listOf(0, 1, 2, 3, 4),
                    title = "title",
                    price = 500.0,
                    publicationStatus = "2",
                    operator = "operator",
                    deleteFlg = "0"
                )
            )
        } catch (e: IllegalStateException) {
            assertEquals("publicationStatusの入力値が不正です。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("bookValidatorで呼び出しているcommonValidatorがCommonValidatorクラスであること")
    fun testCommonValidator() {
        val result = bookValidator!!.commonValidator
        assertEquals(
            commonValidator?.javaClass,
            result.javaClass
        )
    }
}
