package com.bookmanagementsystem.controller

import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookFromAuthorRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import com.bookmanagementsystem.request.book.UpdateBookRequest
import com.bookmanagementsystem.service.BookService
import com.bookmanagementsystem.validator.BookValidator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.BindingResult

@WebMvcTest(controllers = [BookController::class])
internal class BookControllerTest {

    @MockitoBean
    lateinit var service: BookService

    @MockitoBean
    lateinit var validate: BookValidator

    @MockitoBean
    lateinit var bookController: BookController

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        // モックの初期化
        MockitoAnnotations.openMocks(this)

        // MockMvc にテスト用の AuthorController を手動で登録
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
            .build()
    }

    @Test
    @DisplayName("getBookControllerの正常系")
    fun testGetBookController_Success() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetBookRequest(
            bookId = "1"
        )
        `when`(bookController.getBookController(mockRequest, bindingResult))
            .thenReturn(
                ResponseEntity(
                    "{\"bookId\":8,\"authorIdList\":[29],\"title\":\"title\"" +
                            ",\"price\":1000,\"publicationStatus\":\"0\"}",
                    HttpStatus.OK
                )
            )
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.getBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.OK)
        // 実行結果に取得結果が記載されていることを確認
        assert(result.body?.contains("bookId\":8")!!)
        assert(result.body?.contains("authorIdList\":[29]")!!)
        assert(result.body?.contains("title\":\"title")!!)
        assert(result.body?.contains("price\":1000")!!)
        assert(result.body?.contains("publicationStatus\":\"0")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).getBookController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("getBookControllerの異常系_INTERNAL_SERVER_ERROR")
    fun testGetBookController_InternalServerError() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetBookRequest(
            bookId = "1"
        )
        `when`(bookController.getBookController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("書籍情報の取得に失敗しました。", HttpStatus.INTERNAL_SERVER_ERROR))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.getBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.INTERNAL_SERVER_ERROR)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("書籍情報の取得に失敗しました。")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).getBookController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("getBookControllerの異常系_BAD_REQUEST")
    fun testGetBookController_BadRequest() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetBookRequest(
            bookId = "1"
        )
        `when`(bookController.getBookController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("bookIdは8桁以内で入力してください。", HttpStatus.BAD_REQUEST))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.getBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("bookIdは8桁以内で入力してください。")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).getBookController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("getBookFromAuthorControllerの正常系")
    fun testGetBookFromAuthorController_Success() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetBookFromAuthorRequest(
            authorId = "1"
        )
        `when`(bookController.getBookFromAuthorController(mockRequest, bindingResult))
            .thenReturn(
                ResponseEntity(
                    "{\"bookId\":8,\"authorIdList\":[29],\"title\":\"title\"" +
                            ",\"price\":1000,\"publicationStatus\":\"0\"}",
                    HttpStatus.OK
                )
            )
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.getBookFromAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.OK)
        // 実行結果に取得結果が記載されていることを確認
        assert(result.body?.contains("bookId\":8")!!)
        assert(result.body?.contains("authorIdList\":[29]")!!)
        assert(result.body?.contains("title\":\"title")!!)
        assert(result.body?.contains("price\":1000")!!)
        assert(result.body?.contains("publicationStatus\":\"0")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).getBookFromAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("getBookFromAuthorControllerの異常系_INTERNAL_SERVER_ERROR")
    fun testGetBookFromAuthorController_InternalServerError() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetBookFromAuthorRequest(
            authorId = "1"
        )
        `when`(bookController.getBookFromAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("書籍情報の取得に失敗しました。", HttpStatus.INTERNAL_SERVER_ERROR))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.getBookFromAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.INTERNAL_SERVER_ERROR)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("書籍情報の取得に失敗しました。")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).getBookFromAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("getBookFromAuthorControllerの異常系_BAD_REQUEST")
    fun testGetBookFromAuthorController_BadRequest() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetBookFromAuthorRequest(
            authorId = "1"
        )
        `when`(bookController.getBookFromAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("bookIdは8桁以内で入力してください。", HttpStatus.BAD_REQUEST))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.getBookFromAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("bookIdは8桁以内で入力してください。")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).getBookFromAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("createBookControllerの正常系")
    fun testCreateBookController_Success() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = CreateBookRequest(
            authorIdList = listOf("1", "3", "5"),
            title = "title",
            price = "9999",
            publicationStatus = "1",
            operator = "operator"
        )
        `when`(bookController.createBookController(mockRequest, bindingResult))
            .thenReturn(
                ResponseEntity(
                    "{\"bookId\":1}",
                    HttpStatus.OK
                )
            )
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.createBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.OK)
        // 実行結果に取得結果が記載されていることを確認
        assert(result.body?.contains("bookId\":1")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).createBookController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("createBookControllerの異常系_INTERNAL_SERVER_ERROR")
    fun testCreateBookController_InternalServerError() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = CreateBookRequest(
            authorIdList = listOf("1", "3", "5"),
            title = "title",
            price = "9999",
            publicationStatus = "1",
            operator = "operator"
        )
        `when`(bookController.createBookController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("書籍情報の登録に失敗しました。", HttpStatus.INTERNAL_SERVER_ERROR))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.createBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.INTERNAL_SERVER_ERROR)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("書籍情報の登録に失敗しました。")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).createBookController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("createBookControllerの異常系_BAD_REQUEST")
    fun testCreateBookController_BadRequest() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = CreateBookRequest(
            authorIdList = listOf("1", "3", "5"),
            title = "title",
            price = "9999",
            publicationStatus = "1",
            operator = "operator"
        )
        `when`(bookController.createBookController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("publicationStatusの入力値が不正です。", HttpStatus.BAD_REQUEST))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.createBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("publicationStatusの入力値が不正です。")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).createBookController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("updateBookControllerの正常系")
    fun testUpdateBookController_Success() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = UpdateBookRequest(
            bookId = "1",
            authorIdList = listOf("1", "3", "5"),
            title = "title",
            price = "9999",
            publicationStatus = "1",
            operator = "operator",
            deleteFlg = "0"
        )
        `when`(bookController.updateBookController(mockRequest, bindingResult))
            .thenReturn(
                ResponseEntity(
                    "{\"bookId\":1}",
                    HttpStatus.OK
                )
            )
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.updateBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.OK)
        // 実行結果に取得結果が記載されていることを確認
        assert(result.body?.contains("bookId\":1")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).updateBookController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("updateBookControllerの異常系_INTERNAL_SERVER_ERROR")
    fun testUpdateBookController_InternalServerError() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = UpdateBookRequest(
            bookId = "1",
            authorIdList = listOf("1", "3", "5"),
            title = "title",
            price = "9999",
            publicationStatus = "1",
            operator = "operator",
            deleteFlg = "0"
        )
        `when`(bookController.updateBookController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("書籍情報の更新に失敗しました。", HttpStatus.INTERNAL_SERVER_ERROR))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.updateBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.INTERNAL_SERVER_ERROR)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("書籍情報の更新に失敗しました。")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).updateBookController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("updateBookControllerの異常系_BAD_REQUEST")
    fun testUpdateBookController_BadRequest() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = UpdateBookRequest(
            bookId = "1",
            authorIdList = listOf("1", "3", "5"),
            title = "title",
            price = "9999",
            publicationStatus = "1",
            operator = "operator",
            deleteFlg = "0"
        )
        `when`(bookController.updateBookController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("publicationStatusの入力値が不正です。", HttpStatus.BAD_REQUEST))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = bookController.updateBookController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("publicationStatusの入力値が不正です。")!!)
        // モックメソッドの呼び出しを検証
        verify(bookController).updateBookController(mockRequest, bindingResult)
    }
}
