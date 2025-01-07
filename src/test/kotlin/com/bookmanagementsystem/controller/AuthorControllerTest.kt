package com.bookmanagementsystem.controller

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [AuthorController::class])
internal class AuthorControllerTest {
    /*
        @Mock
        var validate: AuthorValidator? = null

        @Mock
        var service: AuthorService? = null
    */
    /*   @Mock
       var authorController: AuthorController? = null

       @Autowired
       private lateinit var mockMvc: MockMvc*/
    /*
        @BeforeEach
        fun setUp() {
            // モックの初期化
            MockitoAnnotations.openMocks(this)
        }*/
    /*
        @Test
        fun `should return 200 and author response for valid GET request`() {
            // Arrange
            val authorId = 1
            //val mockResponse = Author(
            //    id = authorId,
            //    authorName = "John Doe",
            //    birthday = LocalDate.now().minusYears(1),
            //    operator = "opr",
            //    deleteFlg = "0"
            //)
            //`when`(service?.getAuthor(authorId)).thenReturn(mockResponse)
            //`when`(validate?.validGetAuthor(GetAuthorRequest(1)))

            // Act & Assert
            mockMvc.perform(get("/authors/{id}", authorId))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.authorId").value(authorId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.books").isArray)
                .andExpect(jsonPath("$.books[0]").value("Book 1"))
                .andExpect(jsonPath("$.books[1]").value("Book 2"))

            //verify(authorService).getAuthor(authorId)
        }
    }*/
    /*
            @Test
            @Throws(Exception::class)
            fun getExample_shouldReturnExampleResponse() {

               // モックの設定
                val mockResponse = Author(
                    id = 1,
                    authorName = "testName",
                    birthday = LocalDate.now().minusYears(1),
                    operator = null,
                    deleteFlg = null,
                )

                // モックされたserviceのgetAuthorメソッドが呼ばれたとき、mockResponseを返すよう設定。
                `when`(service?.getAuthor(1)).thenReturn(mockResponse)

                // テスト実行
                mockMvc.perform(get("/getAuthor/1"))
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.authorId").value(1))
                    .andExpect(jsonPath("$.authorName").value("testName"))
                    .andExpect(jsonPath("$.birthday").value(LocalDate.now().minusYears(1)))

                // Serviceが1回だけ呼ばれたことを検証
                verify(service, times(1))?.getAuthor(1)

                // assertEquals("birthdayは過去日を設定してください。", e.message)

                // Service2回以上呼ばれたことを検証
                //verify(exampleService, atLeast(2)).getExampleById(testId);

                //一度も呼び出されていない場合の検証：
                //verifyNoInteractions(exampleService);

                // サービスの特定メソッドが呼び出されていないことを検証
                //verify(exampleService, times(0)).getExampleById(any());
            }
    /*
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

     */
}