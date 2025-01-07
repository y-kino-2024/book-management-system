package com.bookmanagementsystem.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class CommonServiceTest {

    @Mock
    private var commonService: CommonService = CommonService()

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @DisplayName("getProcessingDateTimeを呼んだときに処理時刻が取得できること")
    fun testGetProcessingDateTime() {
        // ミリ秒単位での処理時刻の取得となるため、返り値はMock化する
        val mockDateTime = LocalDateTime.now()
        `when`(commonService.getProcessingDateTime()).thenReturn(mockDateTime)
        val result = commonService.getProcessingDateTime()
        assertEquals(mockDateTime, result)
    }
}
