package com.bookmanagementsystem.controller

import com.bookmanagementsystem.entity.Book
import com.bookmanagementsystem.enumkt.PublicationStatus
import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookFromAuthorRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import com.bookmanagementsystem.request.book.UpdateBookRequest
import com.bookmanagementsystem.response.book.CreateBookResponse
import com.bookmanagementsystem.response.book.GetBookResponse
import com.bookmanagementsystem.service.BookService
import com.bookmanagementsystem.validator.BookValidator
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * 書籍のController
 */
@RestController
class BookController(
    val validate: BookValidator,
    val service: BookService
) {
    companion object {
        const val DELETE_FLG_ZERO = "0"

        const val UNKNOWN_ERROR_MESSAGE = "未定義のエラー"
    }

    /**
     * 書籍情報を取得する
     *
     * @args request getBookのリクエスト
     * @return 書籍情報のJSON
     */
    @GetMapping("/getBook")
    fun getBookController(
        @Validated request: GetBookRequest,
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
            validate.validGetBook(request)
            // 書籍取得処理
            val book = service.getBook(request.bookId?.let {
                request.bookId
            } ?: throw IllegalStateException("書籍IDの値が不正です。"))
            // レスポンスに詰め替える
            if (book != null) {
                // 検索結果が取得できた場合
                val response = convertGetBookResponse(book)
                println("controller$response")
                // JSONで返す
                val mapper = ObjectMapper()
                val responseJson = mapper.writeValueAsString(response)
                return responseJson
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
     * 著者IDから書籍情報を取得する
     *
     * @args request getBookFromAuthorのリクエスト
     * @return 書籍情報のJSON
     */
    @GetMapping("/getBookFromAuthor")
    fun getBookFromAuthorController(
        @Validated request: GetBookFromAuthorRequest,
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
            validate.validGetBookFromAuthor(request)
            // 書籍取得処理
            val bookList = service.getBookFromAuthor(request.authorId?.let {
                request.authorId
            } ?: throw IllegalStateException("著者IDの値が不正です。"))
            // レスポンスに詰め替える
            if (bookList != null) {
                // 検索結果が取得できた場合
                val response = mutableListOf<GetBookResponse>()
                for (book in bookList) {
                    response.add(convertGetBookResponse(book))
                }
                // JSONで返す
                val mapper = ObjectMapper()
                // 日付の表示形式を整形
                val responseJson = mapper.writeValueAsString(response)
                return responseJson
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
     * 書籍情報を登録する
     *
     * @args request createBookのリクエスト
     * @return 書籍IDのJSON
     */
    @PostMapping("/createBook")
    fun createBookController(
        @Validated @RequestBody request: CreateBookRequest,
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
            validate.validCreateBook(request)
            // リクエストを詰め替える
            val book = convertBook(request)
            // 書籍登録処理
            val bookId = service.createBook(book)
            // レスポンスに詰め替える
            val response = convertCreateBookResponse(bookId)
            // JSONで返す
            val mapper = ObjectMapper()
            val responseJson = mapper.writeValueAsString(response)
            return responseJson
        } catch (e: Exception) {
            return e.message ?: throw Exception(UNKNOWN_ERROR_MESSAGE)
        }
    }

    /**
     * 書籍情報を更新する
     *
     * @args request updateBookのリクエスト
     * @return 書籍IDのJSON
     */
    @PostMapping("/updateBook")
    fun updateBookController(request: UpdateBookRequest): String {
        // バリデーション処理

        // リクエストを詰め替える

        // 書籍更新処理

        // レスポンスに詰め替える

        // TODO JSONで返す
        return "test"
        // 完了
    }

    /**
     * 書籍取得処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args book 書籍情報
     * @return 書籍情報のレスポンスオブジェクト
     */
    private fun convertGetBookResponse(book: Book): GetBookResponse {
        return GetBookResponse(
            bookId = book.id?.let {
                book.id.toString()
            } ?: throw IllegalStateException("書籍IDの値が不正です。"),
            authorIdList = book.authorIdList?.let {
                book.authorIdList.map { authorId ->
                    authorId.toString()
                }
            } ?: throw IllegalStateException("著者IDの値が不正です。"),
            title = book.title?.let {
                book.title
            } ?: throw IllegalStateException("タイトルの値が不正です。"),
            price = book.price?.let {
                book.price.toInt()
            } ?: throw IllegalStateException("価格の値が不正です。"),
            publicationStatus = book.publicationStatus?.let {
                book.publicationStatus.code
            } ?: throw IllegalStateException("出版状況の値が不正です。"),
        )
    }

    // TODO convertBookを登録と更新で一つにしたい
    /**
     * 書籍登録処理のリクエストを書籍Entityに詰め替える
     *
     * @args request 書籍登録処理のリクエスト
     * @return 書籍Entity
     */
    private fun convertBook(request: CreateBookRequest): Book {
        return Book(
            id = null,
            authorIdList = request.authorIdList,
            title = request.title,
            price = request.price,
            publicationStatus = request.publicationStatus?.let {
                PublicationStatus.getPublicationStatus(request.publicationStatus)
            } ?: throw IllegalStateException("出版状況の値が不正です"),
            operator = request.operator,
            deleteFlg = DELETE_FLG_ZERO
        )
    }

    /**
     * 書籍登録処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args bookId 書籍ID
     * @return 書籍登録結果のレスポンスオブジェクト
     */
    private fun convertCreateBookResponse(bookId: String): CreateBookResponse {
        return CreateBookResponse(
            bookId = bookId
        )
    }
}
