package com.bookmanagementsystem.controller

import com.bookmanagementsystem.entity.Author
import com.bookmanagementsystem.service.AuthorService
import com.bookmanagementsystem.validator.AuthorValidator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@SpringBootTest
internal class AuthorControllerTest {

    @Mock
        var validate: AuthorValidator? = null

    @Mock
        var service: AuthorService? = null

        @InjectMocks
        var authorController: AuthorController? = null

    @Mock
    private lateinit var mockMvc: MockMvc

        @BeforeEach
        fun setUp() {
            MockitoAnnotations.openMocks(this)
        }

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
            mockMvc.perform(get("/getAuthor?authorId=1"))
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
}
