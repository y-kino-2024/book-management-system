package com.bookmanagementsystem.enumkt

/**
 * 出版状況のenum
 */
enum class PublicationStatus(val code: String) {
    UNPUBLISHED(code = "0"),
    PUBLISHED(code = "1");

    companion object {

        /**
         * コードに対応するEnum値を返却する
         *
         * @args code Enumのコード値
         * @return 引数に対応するEnum値
         */
        fun getPublicationStatus(code: String): PublicationStatus {
            return when (code) {
                "0" -> UNPUBLISHED
                "1" -> PUBLISHED
                else -> throw IllegalStateException("出版状況の値が不正です。")
            }
        }

        /**
         * コードに対応するEnum値が存在するか判定する
         *
         * @args code Enumのコード値
         * @return 存在有無
         */
        fun contains(code: String): Boolean {
            for (publicationStatus in entries) {
                if (publicationStatus.code == code) {
                    return true
                }
            }
            return false
        }
    }
}
