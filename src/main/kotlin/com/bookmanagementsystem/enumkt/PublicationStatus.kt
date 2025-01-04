package com.bookmanagementsystem.enumkt

/**
 * 出版状況のenum
 */
enum class PublicationStatus(val code: String) {
    NONE(code = "9"),
    UNPUBLISHED(code = "0"),
    PUBLISHED(code = "1");

    companion object {
        /**
         * コードに対応するEnum値を返却する
         *
         * @param code Enumのコード値
         * @return 引数に対応するEnum値
         */
        fun getPublicationStatus(code: String): PublicationStatus {
            return when (code) {
                "9" -> NONE
                "0" -> UNPUBLISHED
                "1" -> PUBLISHED
                else -> throw IllegalStateException("出版状況の値が不正です。")
            }
        }
    }
}
