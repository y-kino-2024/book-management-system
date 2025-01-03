package com.bookmanagementsystem.controller

import com.bookmanagementsystem.entity.Author
import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import com.bookmanagementsystem.response.author.CreateAuthorResponse
import com.bookmanagementsystem.response.author.GetAuthorResponse
import com.bookmanagementsystem.service.AuthorService
import com.bookmanagementsystem.validator.AuthorValidator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter


/**
 * 著者のController
 */
@RestController
class AuthorController(
    val validate: AuthorValidator,
    val service: AuthorService
) {
    companion object {
        const val DELETE_FLG_ZERO = "0"

        const val UNKNOWN_ERROR_MESSAGE = "未定義のエラー"
    }

    /**
     * 著者情報を取得する
     *
     * @args request getAuthorのリクエスト
     * @return 著者情報のJSON
     */
    @GetMapping("/getAuthor")
    fun getAuthorController(
        @Validated request: GetAuthorRequest,
        bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            val responseJson = mapper.writeValueAsString(errorMessageList)
            return responseJson
        }
        try {
            // バリデーション処理
            validate.validGetAuthor(request)
            // 著者取得処理
            val author = service.getAuthor(request.authorId.toString())
            // レスポンスに詰め替える
            if (author != null) {
                // 検索結果が取得できた場合
                val response = convertGetAuthorResponse(author)
                println("controller$response")
                // JSONで返す
                val mapper = ObjectMapper()
                // JSONをLocalDateに対応させる
                val timeModule = JavaTimeModule()
                timeModule.addDeserializer(
                    LocalDate::class.java,
                    LocalDateDeserializer(DateTimeFormatter.ISO_DATE_TIME)
                )
                mapper.registerModule(timeModule)
                // 日付の表示形式を整形
                mapper.dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd")
                val responseJson = mapper.writeValueAsString(response)
                return responseJson
            } else {
                // 検索結果が取得できなかった場合
                val mapper = ObjectMapper()
                return mapper.writeValueAsString("取得対象が存在しません。")
            }
        } catch (e: Exception) {
            return e.message ?: throw Exception("エラーメッセージ未設定")
        }
    }

    /**
     * 著者情報を登録する
     *
     * @args request createAuthorのリクエスト
     * @return 著者IDのJSON
     */
    @PostMapping("/createAuthor")
    fun createAuthorController(
        @Validated @RequestBody request: CreateAuthorRequest,
        bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            val responseJson = mapper.writeValueAsString(errorMessageList)
            return responseJson
        }
        try {
            // バリデーション処理
            validate.validCreateAuthor(request)
            // リクエストを詰め替える
            val author = convertAuthor(request)

            // 著者登録処理
            val authorId = service.createAuthor(author)

            // レスポンスに詰め替える
            val response = convertCreateAuthorResponse(authorId)

            // JSONで返す
            val mapper = ObjectMapper()
            val responseJson = mapper.writeValueAsString(response)
            return responseJson
        } catch (e: Exception) {
            return e.message ?: throw Exception("エラーメッセージ未設定")
        }
    }

    /**
     * 著者情報を更新する
     *
     * @args request updateAuthorのリクエスト
     * @return 著者IDのJSON
     */
    @PostMapping("/updateAuthor")
    fun updateAuthorController(
        @Validated @RequestBody request: UpdateAuthorRequest,
        bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            val responseJson = mapper.writeValueAsString(errorMessageList)
            return responseJson
        }
        try {
            // バリデーション処理
            validate.validUpdateAuthor(request)
            // リクエストを詰め替える
            val author = convertAuthor(request)
            // 著者更新処理
            val authorId = service.updateAuthor(author)
            // レスポンスに詰め替える
            val response = convertCreateAuthorResponse(authorId)
            // JSONで返す
            val mapper = ObjectMapper()
            val responseJson = mapper.writeValueAsString(response)
            return responseJson
            //return "test"
            // 完了
        } catch (e: Exception) {
            return e.message ?: throw Exception("エラーメッセージ未設定")
        }
    }

    /**
     * 著者取得処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args author 著者情報
     * @return 著者情報のレスポンスオブジェクト
     */
    private fun convertGetAuthorResponse(author: Author): GetAuthorResponse {
        return GetAuthorResponse(
            authorId = author.id?.let {
                author.id.toString()
            } ?: throw IllegalStateException("著者IDが不正です。"),
            authorName = author.authorName?.let {
                author.authorName.toString()
            } ?: throw IllegalStateException("著者名が不正です。"),
            birthday = LocalDate.parse(author.birthday.toString())
        )
    }

    /**
     * 著者登録処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args authorId 著者ID
     * @return 著者登録結果のレスポンスオブジェクト
     */
    private fun convertCreateAuthorResponse(authorId: String): CreateAuthorResponse {
        return CreateAuthorResponse(
            authorId = authorId
        )
    }

    // TODO convertAuthorを登録と更新で一つにしたい
    /**
     * 著者登録処理のリクエストを著者Entityに詰め替える
     *
     * @args request 著者登録処理のリクエスト
     * @return 著者Entity
     */
    private fun convertAuthor(request: CreateAuthorRequest): Author {
        return Author(
            id = null,
            authorName = request.authorName?.let {
                request.authorName.toString()
            } ?: throw IllegalStateException("著者名が不正です。"),
            birthday = request.birthday?.let {
                request.birthday
            } ?: throw IllegalStateException("誕生日が不正です。"),
            operator = request.operator?.let {
                request.operator.toString()
            } ?: throw IllegalStateException("操作者が不正です。"),
            // 削除フラグは作成時には「0：未削除」をいれる
            deleteFlg = DELETE_FLG_ZERO
        )
    }

    /**
     * 著者更新処理のリクエストを著者Entityに詰め替える
     *
     * @args request 著者更新処理のリクエスト
     * @return 著者Entity
     */
    private fun convertAuthor(request: UpdateAuthorRequest): Author {
        return Author(
            id = request.authorId?.let {
                request.authorId.toInt()
            } ?: throw IllegalStateException("著者IDが不正です。"),
            authorName = request.authorName,
            birthday = request.birthday,
            operator = request.operator,
            deleteFlg = request.deleteFlg
        )
    }
}
