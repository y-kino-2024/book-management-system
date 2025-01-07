package com.bookmanagementsystem.controller

import com.bookmanagementsystem.entity.Author
import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import com.bookmanagementsystem.response.author.CreateAuthorResponse
import com.bookmanagementsystem.response.author.GetAuthorResponse
import com.bookmanagementsystem.response.author.UpdateAuthorResponse
import com.bookmanagementsystem.service.AuthorService
import com.bookmanagementsystem.validator.AuthorValidator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import org.springframework.beans.factory.annotation.Autowired
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
    @Autowired
    val validate: AuthorValidator,
    @Autowired
    val service: AuthorService
) {
    companion object {
        const val DELETE_FLG_ZERO = "0"

        const val UNKNOWN_ERROR_MESSAGE = "未定義のエラー"

        const val UPDATE_TARGET_NOT_EXIST_MESSAGE = "更新対象が存在しません。"
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
        // GetAuthorRequest内のアノテーションによるバリデーションチェックでエラーが発生した場合の処理
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            return mapper.writeValueAsString(errorMessageList)
        }
        try {
            // バリデーション処理
            validate.validGetAuthor(request)
            // 著者取得処理
            val author = service.getAuthor(request.authorId?.let {
                // nullチェック済みのためここではnullにならない
                request.authorId
            } ?: throw IllegalStateException("authorIdの値が不正です。"))
            // レスポンスに詰め替える
            if (author != null) {
                // 検索結果が取得できた場合
                val response = convertGetAuthorResponse(author)
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
                return mapper.writeValueAsString(response)
            } else {
                // 検索結果が取得できなかった場合
                val mapper = ObjectMapper()
                return mapper.writeValueAsString("取得対象が存在しません。")
            }
        } catch (e: Exception) {
            return e.message ?: throw Exception(UNKNOWN_ERROR_MESSAGE)
        }
    }

    /**
     * 著者情報を登録する
     *
     * @args request createAuthorのリクエスト
     * @return 登録した著者IDのJSON
     */
    @PostMapping("/createAuthor")
    fun createAuthorController(
        @Validated @RequestBody request: CreateAuthorRequest,
        bindingResult: BindingResult
    ): String {
        // CreateAuthorRequest内のアノテーションによるバリデーションチェックでエラーが発生した場合の処理
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            return mapper.writeValueAsString(errorMessageList)
        }
        try {
            // バリデーション処理
            validate.validCreateAuthor(request)
            // リクエストを詰め替える
            val author = convertCreateAuthor(request)
            // 著者登録処理
            val authorId = service.createAuthor(author)
            // レスポンスに詰め替える
            val response = convertCreateAuthorResponse(authorId)
            // JSONで返す
            val mapper = ObjectMapper()
            return mapper.writeValueAsString(response)
        } catch (e: Exception) {
            return e.message ?: throw Exception(UNKNOWN_ERROR_MESSAGE)
        }
    }

    /**
     * 著者情報を更新する
     *
     * @args request updateAuthorのリクエスト
     * @return 更新対象の著者IDのJSON
     */
    @PostMapping("/updateAuthor")
    fun updateAuthorController(
        @Validated @RequestBody request: UpdateAuthorRequest,
        bindingResult: BindingResult
    ): String {
        // UpdateAuthorRequest内のアノテーションによるバリデーションチェックでエラーが発生した場合の処理
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            return mapper.writeValueAsString(errorMessageList)
        }
        try {
            // バリデーション処理
            validate.validUpdateAuthor(request)
            // リクエストを詰め替える
            val author = convertUpdateAuthor(request)
            // 著者更新処理
            val authorId = service.updateAuthor(author)
            // JSONで返す
            val mapper = ObjectMapper()
            if (authorId == null) {
                // 更新対象が存在しない場合はメッセージを返す
                return mapper.writeValueAsString(UPDATE_TARGET_NOT_EXIST_MESSAGE)
            }
            // レスポンスに詰め替える
            val response = convertUpdateAuthorResponse(authorId)
            return mapper.writeValueAsString(response)
        } catch (e: Exception) {
            return e.message ?: throw Exception(UNKNOWN_ERROR_MESSAGE)
        }
    }

    /**
     * 著者取得処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args author 著者Entity
     * @return 著者取得処理のレスポンスオブジェクト
     */
    private fun convertGetAuthorResponse(author: Author): GetAuthorResponse {
        return GetAuthorResponse(
            authorId = author.id?.let {
                // authorIdは主キーのためここでnullが入ることはない
                author.id
            } ?: throw IllegalStateException("authorIdの値が不正です。"),
            authorName = author.authorName?.let {
                // authorNameはDBの必須項目となっているためここでnullが入ることはない
                author.authorName.toString()
            } ?: throw IllegalStateException("authorNameの値が不正です。"),
            birthday = author.birthday?.let {
                // birthdayはDBの必須項目となっているためここでnullが入ることはない
                author.birthday
            } ?: throw IllegalStateException("birthdayの値が不正です。"),
        )
    }

    /**
     * 著者登録処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args authorId 著者ID
     * @return 著者登録処理のレスポンスオブジェクト
     */
    private fun convertCreateAuthorResponse(authorId: Int): CreateAuthorResponse {
        return CreateAuthorResponse(
            authorId = authorId
        )
    }

    /**
     * 著者更新処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args authorId 著者ID
     * @return 著者更新結果のレスポンスオブジェクト
     */
    private fun convertUpdateAuthorResponse(authorId: Int): UpdateAuthorResponse {
        return UpdateAuthorResponse(
            authorId = authorId
        )
    }

    /**
     * 著者登録処理のリクエストを著者Entityに詰め替える
     *
     * @args request 著者登録処理のリクエスト
     * @return 著者Entity
     */
    private fun convertCreateAuthor(request: CreateAuthorRequest): Author {
        return Author(
            id = null,
            authorName = request.authorName?.let {
                // authorNameは必須チェック済みのためここでnullが入ることはない
                request.authorName.toString()
            } ?: throw IllegalStateException("authorNameの値が不正です。"),
            birthday = request.birthday?.let {
                // birthdayは必須チェック済みのためここでnullが入ることはない
                request.birthday
            } ?: throw IllegalStateException("birthdayの値が不正です。"),
            operator = request.operator?.let {
                // operatorは必須チェック済みのためここでnullが入ることはない
                request.operator.toString()
            } ?: throw IllegalStateException("operatorの値が不正です。"),
            // 登録処理時には削除フラグは「0：未削除」をいれる
            deleteFlg = DELETE_FLG_ZERO
        )
    }

    /**
     * 著者更新処理のリクエストを著者Entityに詰め替える
     *
     * @args request 著者更新処理のリクエスト
     * @return 著者Entity
     */
    private fun convertUpdateAuthor(request: UpdateAuthorRequest): Author {
        return Author(
            id = request.authorId?.let {
                // authorIdは必須チェック済みのためここでnullが入ることはない
                request.authorId
            } ?: throw IllegalStateException("authorIdの値が不正です。"),
            authorName = request.authorName,
            birthday = request.birthday,
            operator = request.operator,
            deleteFlg = request.deleteFlg
        )
    }
}
