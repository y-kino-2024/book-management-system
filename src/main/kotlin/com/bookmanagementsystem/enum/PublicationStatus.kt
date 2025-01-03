package com.bookmanagementsystem.enum

/**
 * 出版状況のenum
 */
enum class PublicationStatus(val code: String) {
    NONE(code = "9"),
    UNPUBLISHED(code = "0"),
    PUBLISHED(code = "1");

    /**
     * コードに対応するEnum値を返却する
     *
     * @param code Enumのコード値
     * @return 引数に対応するEnum値
     */
    /*fun getPublicationStatus(code: String): PublicationStatus {
       return PublicationStatus.valueOf(code)
   }*/
}
