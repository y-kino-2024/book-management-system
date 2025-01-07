package com.bookmanagementsystem.validator

import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
internal class AuthorValidatorTest {

    @Mock
    var commonValidator: CommonValidator? = null

    @InjectMocks
    var authorValidator: AuthorValidator? = null

    @Test
    @DisplayName("著者取得処理のバリデーションチェックにて、全ての値が正常値の場合はエラーとならないこと")
    fun testValidGetAuthor_Success() {
        try {
            authorValidator!!.validGetAuthor(GetAuthorRequest(0))
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者取得処理のバリデーションチェックにて、authorIdがnullの場合はNullPointerExceptionが投げられること")
    fun testValidGetAuthor_NullPointerException() {
        try {
            authorValidator!!.validGetAuthor(GetAuthorRequest(null))
            fail("例外がthrowされませんでした")
        } catch (e: NullPointerException) {
            assertEquals("authorIdを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("著者登録処理のバリデーションチェックにて、全ての値が正常値の場合はエラーとならないこと")
    fun testValidCreateAuthor_Success() {
        try {
            authorValidator!!.validCreateAuthor(
                CreateAuthorRequest(
                    authorName = "authorName",
                    birthday = LocalDate.now(),
                    operator = "operator"
                )
            )
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者登録処理のバリデーションチェックにて、authorNameがnullの場合はNullPointerExceptionが投げられること")
    fun testValidCreateAuthor_NullPointerException_AuthorName() {
        try {
            authorValidator!!.validCreateAuthor(
                CreateAuthorRequest(
                    authorName = null,
                    birthday = LocalDate.now(),
                    operator = "operator"
                )
            )
            fail("例外がthrowされませんでした")
        } catch (e: NullPointerException) {
            assertEquals("authorNameを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("著者登録処理のリクエストが設定されており、birthdayの必須チェックでエラーとなった場合はNullPointerExceptionが投げられること")
    fun testValidCreateAuthor_NullPointerException_Birthday() {
        try {
            authorValidator!!.validCreateAuthor(
                CreateAuthorRequest(
                    authorName = "authorName",
                    birthday = null,
                    operator = "operator"
                )
            )
            fail("例外がthrowされませんでした")
        } catch (e: NullPointerException) {
            assertEquals("birthdayを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("著者登録処理のリクエストが設定されており、birthdayの過去日チェックでエラーとなった場合はIllegalStateExceptionが投げられること")
    fun testValidCreateAuthor_IllegalStateException_Birthday() {
        try {
            authorValidator!!.validCreateAuthor(
                CreateAuthorRequest(
                    authorName = "authorName",
                    birthday = LocalDate.now().plusDays(1),
                    operator = "operator"
                )
            )
            fail("例外がthrowされませんでした")
        } catch (e: IllegalStateException) {
            assertEquals("birthdayは過去日を設定してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("著者更新処理のバリデーションチェックにて、全ての値が正常値の場合はエラーとならないこと")
    fun testValidUpdateAuthor_Success() {
        try {
            authorValidator!!.validUpdateAuthor(
                UpdateAuthorRequest(
                    authorId = 0,
                    authorName = "authorName",
                    birthday = LocalDate.now(),
                    operator = "operator",
                    deleteFlg = "0"
                )
            )
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("著者更新処理のリクエストが設定されており、authorIdの必須チェックでエラーとなった場合はNullPointerExceptionが投げられること")
    fun testValidUpdateAuthor_NullPointerException_AuthorId() {
        try {
            authorValidator!!.validUpdateAuthor(
                UpdateAuthorRequest(
                    authorId = null,
                    authorName = "authorName",
                    birthday = LocalDate.now(),
                    operator = "operator",
                    deleteFlg = "0"
                )
            )
            fail("例外がthrowされませんでした")
        } catch (e: NullPointerException) {
            assertEquals("authorIdを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("著者更新処理のリクエストが設定されており、birthdayの過去日チェックでエラーとなった場合はIllegalStateExceptionが投げられること")
    fun testValidUpdateAuthor_IllegalStateException_Birthday() {
        try {
            authorValidator!!.validUpdateAuthor(
                UpdateAuthorRequest(
                    authorId = 0,
                    authorName = "authorName",
                    birthday = LocalDate.now().plusDays(1),
                    operator = "operator",
                    deleteFlg = "0"
                )
            )
            fail("例外がthrowされませんでした")
        } catch (e: IllegalStateException) {
            assertEquals("birthdayは過去日を設定してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("authorValidatorで呼び出しているcommonValidatorがCommonValidatorクラスであること")
    fun testCommonValidator() {
        val result = authorValidator!!.commonValidator
        assertEquals(
            commonValidator?.javaClass,
            result.javaClass
        )
    }
}
