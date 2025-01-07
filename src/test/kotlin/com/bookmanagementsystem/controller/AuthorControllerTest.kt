package com.bookmanagementsystem.controller

import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class AuthorControllerTest {
    /*    @MockitoBean
        var validate: AuthorValidator? = null

        @MockitoBean
        var service: AuthorService? = null

        @InjectMocks
        var authorController: AuthorController? = null

        @Autowired
        private val mockMvc: MockMvc? = null

        @BeforeEach
        fun setUp() {
            MockitoAnnotations.openMocks(this)
        }

        @Test
        @Throws(Exception::class)
        fun getExample_shouldReturnExampleResponse() {

            // モックの設定
            val testId = 1
            val mockResponse = Author(
                id = 1,
                authorName = "testName",
                birthday = LocalDate.now().minusYears(1),
                operator = null,
                deleteFlg = null,
            )

            // モックされたserviceのgetAuthorメソッドが呼ばれたとき、mockResponseを返すよう設定。
            mockWhen(service?.getAuthor(1)).thenReturn(mockResponse)

            // テスト実行
            mockMvc!!.perform(
                get("/getAuthor")
                    .contentType(MediaType.APPLICATION_JSON)
                    .(status().isOk()) // HTTPステータスコード200（OK）を期待。
                    .andExpect(jsonPath("$.id").value(1)) // JSONの"id"フィールドを検証
                    .andExpect(jsonPath("$.message").value("Mocked Example 1")) // レスポンスJSONの"message"フィールドが期待通りであることを検証。

            )

            // Serviceが1回だけ呼ばれたことを検証
            verify(service, times(1))?.getAuthor(testId)

            // assertEquals("birthdayは過去日を設定してください。", e.message)

            // Service2回以上呼ばれたことを検証
            //verify(exampleService, atLeast(2)).getExampleById(testId);

            //一度も呼び出されていない場合の検証：
            //verifyNoInteractions(exampleService);

            // サービスの特定メソッドが呼び出されていないことを検証
            //verify(exampleService, times(0)).getExampleById(any());
        }

        @Test
        fun testGetValidate() {
            val result = authorController!!.validate
            assertEquals(
                validate?.javaClass,
                result.javaClass
            )
        }

        @Test
        fun testGetService() {
            val result = authorController!!.service
            assertEquals(
                service?.javaClass, result.javaClass
            )
        }
    */
}
