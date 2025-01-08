package com.bookmanagementsystem.enumkt

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PublicationStatusTest {

    // 未出版のEnum定義
    val publicationStatusUnPublished = PublicationStatus.UNPUBLISHED

    // 出版済みのEnum定義
    val publicationStatusPublished = PublicationStatus.PUBLISHED

    @Test
    @DisplayName("PublicationStatusが未出版の場合のEnum値とCodeとordinalの確認")
    fun testPublicationStatus_UnPublished() {
        assertEquals(PublicationStatus.UNPUBLISHED, publicationStatusUnPublished)
        assertEquals("0", publicationStatusUnPublished.code)
        assertEquals(0, publicationStatusUnPublished.ordinal)
    }

    @Test
    @DisplayName("PublicationStatusが出版済みの場合のEnum値とCodeとordinalの確認")
    fun testPublicationStatus_Published() {
        assertEquals(PublicationStatus.PUBLISHED, publicationStatusPublished)
        assertEquals("1", publicationStatusPublished.code)
        assertEquals(1, publicationStatusPublished.ordinal)
    }

    @Test
    @DisplayName("getPublicationStatusにUNPUBLISHEDを取得した場合のEnum値とCodeとordinalの確認")
    fun testGetPublicationStatus_UnPublished() {
        try {
            val publicationStatus = PublicationStatus.getPublicationStatus("0")
            assertEquals(PublicationStatus.UNPUBLISHED, publicationStatus)
            assertEquals("0", publicationStatus.code)
            assertEquals(0, publicationStatus.ordinal)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("getPublicationStatusにPUBLISHEDを取得した場合のEnum値とCodeとordinalの確認")
    fun testGetPublicationStatus_Published() {
        try {
            val publicationStatus = PublicationStatus.getPublicationStatus("1")
            assertEquals(PublicationStatus.PUBLISHED, publicationStatus)
            assertEquals("1", publicationStatus.code)
            assertEquals(1, publicationStatus.ordinal)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("getPublicationStatusに未定義の値を渡した際にIllegalStateExceptionが投げられること")
    fun testGetPublicationStatus_IllegalStateException() {
        try {
            PublicationStatus.getPublicationStatus("2")
            fail("例外がthrowされませんでした")
        } catch (e: IllegalStateException) {
            assertEquals("出版状況の値が不正です。", e.message)
        } catch (e: Exception) {
            fail("想定した例外がthrowされませんでした")
        }
    }

    @Test
    @DisplayName("containsにUNPUBLISHEDのCode値を渡した際にtrueが返ってくること")
    fun testContains_UnPublished() {
        try {
            val result = PublicationStatus.contains("0")
            assertEquals(true, result)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("containsにPUBLISHEDのCode値を渡した際にtrueが返ってくること")
    fun testContains_Published() {
        try {
            val result = PublicationStatus.contains("1")
            assertEquals(true, result)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }

    @Test
    @DisplayName("containsに未定義の値を渡した際にfalseが返ってくること")
    fun testContains_Undefined() {
        try {
            val result = PublicationStatus.contains("2")
            assertEquals(false, result)
        } catch (e: Exception) {
            fail("例外がthrowされました")
        }
    }
}
