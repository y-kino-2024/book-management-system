package com.bookmanagementsystem.controller

import com.bookmanagementsystem.entity.Author
import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import com.bookmanagementsystem.response.author.GetAuthorResponse
import com.bookmanagementsystem.service.AuthorService
import com.bookmanagementsystem.validator.AuthorValidator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * 著者のController
 */
@RestController
class AuthorController(
    val validate: AuthorValidator,
    val service: AuthorService
) {
    private val userName: String = "postgres"
    private val password: String = "local_pos_adm1n"
    private val url: String = "jdbc:postgresql://localhost:5432/quo_assignment_db"

    /**
     * 著者情報を取得する
     * @args request getAuthorのリクエスト
     * @return 著者情報のJSON
     */
    @GetMapping("/getAuthor")
    fun getAuthorController(request: GetAuthorRequest): String {
        try {
            //Connection conn = DriverManager.getConnection(url, userName, password)
            // バリデーション処理
            validate.validGetAuthor(request)
            // 著者取得処理
            val author = service.getAuthor(authorId = request.authorId?.let {
                request.authorId.toString()
            } ?: throw IllegalStateException("筆者IDが不正です。"))
            // レスポンスに詰め替える
            val response = convertGetAuthorResponse(author)
            // JSONで返す
            val mapper = ObjectMapper()
            // JSONをLocalDateTimeに対応させる
            val timeModule = JavaTimeModule()
            timeModule.addDeserializer<LocalDateTime>(
                LocalDateTime::class.java,
                LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME)
            )
            mapper.registerModule(timeModule)
            // 日付の表示形式を整形
            mapper.dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd")
            val responseJson = mapper.writeValueAsString(response)
            return responseJson
        } catch (e: Exception) {
            return e.message!!
        }
    }

    /**
     * 著者情報を登録する
     * @args request createAuthorのリクエスト
     * @return 著者IDのJSON
     */
    @PostMapping("/createAuthor")
    fun createAuthorController(request: CreateAuthorRequest): String {
        // バリデーション処理
        validate.validCreateAuthor(request)
        // リクエストを詰め替える
        //val author = convertAuthor(request)

        // 著者登録処理
        //val authorId = service.createAuthor(author = author)

        // レスポンスに詰め替える
        //val response = convertCreateAuthorResponse(author)

        // JSONで返す
        //val mapper = ObjectMapper()
        //val responseJson = mapper.writeValueAsString(response)
        //return responseJson
        // TODO 動確用返り値
        return "test"
    }

    /**
     * 著者情報を更新する
     * @args request updateAuthorのリクエスト
     * @return 著者IDのJSON
     */
    @PostMapping("/updateAuthor")
    fun updateAuthorController(request: UpdateAuthorRequest): String {
        // バリデーション処理

        // リクエストを詰め替える

        // 著者更新処理

        // レスポンスに詰め替える

        // TODO JSONで返す
        return "test"
        // 完了
    }

    /**
     * 著者取得処理の結果をレスポンスオブジェクトに詰め替える
     * @args author 著者情報
     * @return 著者情報のレスポンスオブジェクト
     */
    private fun convertGetAuthorResponse(author: Author): GetAuthorResponse {
        return GetAuthorResponse(
            authorId = author.id,
            authorName = author.authorName,
            birthday = LocalDate.parse(author.birthday.toString())
        )
    }
}
