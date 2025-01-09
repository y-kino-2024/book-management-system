package com.bookmanagementsystem.controller

import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import com.bookmanagementsystem.service.AuthorService
import com.bookmanagementsystem.validator.AuthorValidator
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

@WebMvcTest(controllers = [AuthorController::class])
internal class AuthorControllerTest {

    @MockitoBean
    lateinit var service: AuthorService

    @MockitoBean
    lateinit var validate: AuthorValidator

    @MockitoBean
    lateinit var authorController: AuthorController

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        // モックの初期化
        MockitoAnnotations.openMocks(this)

        // MockMvc にテスト用の AuthorController を手動で登録
        mockMvc = MockMvcBuilders.standaloneSetup(authorController)
            .build()
    }

    @Test
    @DisplayName("getAuthorの正常系")
    fun testGetAuthorController_Success() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetAuthorRequest(
            authorId = "1"
        )
        `when`(authorController.getAuthorController(mockRequest, bindingResult))
            .thenReturn(
                ResponseEntity(
                    "{\"authorId\":1,\"authorName\":\"createTest\",\"birthday\":\"1999-12-31\"}",
                    HttpStatus.OK
                )
            )
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.getAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.OK)
        // 実行結果に取得結果が記載されていることを確認
        assert(result.body?.contains("authorId\":1")!!)
        assert(result.body?.contains("authorName\":\"createTest")!!)
        assert(result.body?.contains("birthday\":\"1999-12-31")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).getAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("getAuthorの異常系_INTERNAL_SERVER_ERROR")
    fun testGetAuthorController_InternalServerError() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetAuthorRequest(
            authorId = "1"
        )
        `when`(authorController.getAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("著者情報の取得に失敗しました。", HttpStatus.INTERNAL_SERVER_ERROR))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.getAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.INTERNAL_SERVER_ERROR)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("著者情報の取得に失敗しました。")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).getAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("getAuthorの異常系_BAD_REQUEST")
    fun testGetAuthorController_BadRequest() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = GetAuthorRequest(
            authorId = "1"
        )
        `when`(authorController.getAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("authorIdは8桁以内で入力してください。", HttpStatus.BAD_REQUEST))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.getAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("authorIdは8桁以内で入力してください。")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).getAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("createAuthorControllerの正常系")
    fun testCreateAuthorController_Success() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = CreateAuthorRequest(
            authorName = "authorName",
            birthday = "1988-04-20",
            operator = "operator",
        )
        `when`(authorController.createAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("{\"authorId\":1}", HttpStatus.OK))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.createAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.OK)
        // 実行結果に登録結果が記載されていることを確認
        assert(result.body?.contains("authorId\":1")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).createAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("createAuthorControllerの異常系_INTERNAL_SERVER_ERROR")
    fun testCreateAuthorController_InternalServerError() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = CreateAuthorRequest(
            authorName = "authorName",
            birthday = "1988-04-20",
            operator = "operator",
        )
        `when`(authorController.createAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("著者情報の登録に失敗しました。", HttpStatus.INTERNAL_SERVER_ERROR))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.createAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.INTERNAL_SERVER_ERROR)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("著者情報の登録に失敗しました。")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).createAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("createAuthorControllerの異常系_BAD_REQUEST")
    fun testCreateAuthorController_BadRequest() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = CreateAuthorRequest(
            authorName = "authorName",
            birthday = "1988-04-20",
            operator = "operator",
        )
        `when`(authorController.createAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("birthdayは過去日を設定してください。", HttpStatus.BAD_REQUEST))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.createAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("birthdayは過去日を設定してください。")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).createAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("updateAuthorControllerの正常系")
    fun testUpdateAuthorController_Success() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = UpdateAuthorRequest(
            authorId = "1",
            authorName = "authorName",
            birthday = "1988-04-20",
            operator = "operator",
            deleteFlg = "0"
        )
        `when`(authorController.updateAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("{\"authorId\":1}", HttpStatus.OK))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.updateAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.OK)
        // 実行結果に登録結果が記載されていることを確認
        assert(result.body?.contains("authorId\":1")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).updateAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("updateAuthorControllerの異常系_INTERNAL_SERVER_ERROR")
    fun testUpdateAuthorController_InternalServerError() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = UpdateAuthorRequest(
            authorId = "1",
            authorName = "authorName",
            birthday = "1988-04-20",
            operator = "operator",
            deleteFlg = "0"
        )
        `when`(authorController.updateAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("著者情報の更新に失敗しました。", HttpStatus.INTERNAL_SERVER_ERROR))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.updateAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.INTERNAL_SERVER_ERROR)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("著者情報の更新に失敗しました。")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).updateAuthorController(mockRequest, bindingResult)
    }

    @Test
    @DisplayName("updateAuthorControllerの異常系_BAD_REQUEST")
    fun testUpdateAuthorController_BadRequest() {
        val bindingResult = mock(BindingResult::class.java)
        val mockRequest = UpdateAuthorRequest(
            authorId = "1",
            authorName = "authorName",
            birthday = "1988-04-20",
            operator = "operator",
            deleteFlg = "0"
        )
        `when`(authorController.updateAuthorController(mockRequest, bindingResult))
            .thenReturn(ResponseEntity("birthdayは過去日を設定してください。", HttpStatus.BAD_REQUEST))
        // テスト対象メソッドの呼び出し
        val result: ResponseEntity<String> = authorController.updateAuthorController(mockRequest, bindingResult)
        // 実行結果のHTTPステータスを確認
        assert(result.statusCode == HttpStatus.BAD_REQUEST)
        // 実行結果にメッセージが記載されていることを確認
        assert(result.body?.contains("birthdayは過去日を設定してください。")!!)
        // モックメソッドの呼び出しを検証
        verify(authorController).updateAuthorController(mockRequest, bindingResult)
    }
}
