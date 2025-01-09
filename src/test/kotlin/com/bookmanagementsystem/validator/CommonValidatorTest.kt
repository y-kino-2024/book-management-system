package com.bookmanagementsystem.validator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.InjectMocks
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CommonValidatorTest {

    @InjectMocks
    var commonValidator: CommonValidator = CommonValidator()

    @Test
    @DisplayName("操作者の必須チェックにて、値が入っている場合はエラーとならないこと")
    fun testCheckOperator() {
        commonValidator.checkOperator("operator")
    }

    @Test
    @DisplayName("操作者の必須チェックにて、値が入っていない場合はNullPointerExceptionが投げられること")
    fun testCheckOperator_NullPointerException() {
        try {
            commonValidator.checkOperator(null)
            fail("例外がthrowされませんでした")
        } catch (e: NullPointerException) {
            assertEquals("operatorを入力してください。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }
}
