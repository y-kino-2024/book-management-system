package com.bookmanagementsystem.controller

import com.bookmanagementsystem.entity.Book
import com.bookmanagementsystem.enumkt.PublicationStatus
import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookFromAuthorRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import com.bookmanagementsystem.request.book.UpdateBookRequest
import com.bookmanagementsystem.response.book.CreateBookResponse
import com.bookmanagementsystem.response.book.GetBookResponse
import com.bookmanagementsystem.response.book.UpdateBookResponse
import com.bookmanagementsystem.service.AuthorService
import com.bookmanagementsystem.service.BookService
import com.bookmanagementsystem.validator.BookValidator
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    @Autowired
    private val validate: BookValidator,
    @Autowired
    private val bookService: BookService,
    @Autowired
    private val authorService: AuthorService,
) {
    companion object {
        const val DELETE_FLG_ZERO = "0"

        const val UNKNOWN_ERROR_MESSAGE = "未定義のエラー"

        const val GET_TARGET_NOT_EXIST_MESSAGE = "取得対象が存在しません。"

        const val UPDATE_TARGET_NOT_EXIST_MESSAGE = "更新対象が存在しません。"
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
    ): ResponseEntity<String> {
        // GetBookRequest内のアノテーションによるバリデーションチェックでエラーが発生した場合の処理
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(errorMessageList))
        }
        try {
            // バリデーション処理
            validate.validGetBook(request)
            // 書籍取得処理
            val book = bookService.getBook(request.bookId?.let {
                // nullチェック済みのためここではnullにならない
                request.bookId.toInt()
            } ?: throw IllegalStateException("bookIdの値が不正です。"))
            val mapper = ObjectMapper()
            // レスポンスに詰め替える
            if (book != null) {
                // 検索結果が取得できた場合
                val response = convertGetBookResponse(book)
                // JSONで返す
                return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response))
            } else {
                // 検索結果が取得できなかった場合
                return ResponseEntity.status(HttpStatus.OK)
                    .body(mapper.writeValueAsString(GET_TARGET_NOT_EXIST_MESSAGE))
            }
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message ?: UNKNOWN_ERROR_MESSAGE)
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
    ): ResponseEntity<String> {
        // GetBookFromAuthorRequest内のアノテーションによるバリデーションチェックでエラーが発生した場合の処理
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(errorMessageList))
        }
        try {
            // バリデーション処理
            validate.validGetBookFromAuthor(request)
            // 書籍取得処理
            val bookList = bookService.getBookFromAuthor(request.authorId?.let {
                // nullチェック済みのためここではnullにならない
                request.authorId.toInt()
            } ?: throw IllegalStateException("著者IDの値が不正です。"))
            val mapper = ObjectMapper()
            // レスポンスに詰め替える
            if (bookList != null) {
                // 検索結果が取得できた場合レスポンスに詰めなおす
                val response = mutableListOf<GetBookResponse>()
                for (book in bookList) {
                    response.add(convertGetBookResponse(book))
                }
                // JSONで返す
                return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response))
            } else {
                // 検索結果が取得できなかった場合
                return ResponseEntity.status(HttpStatus.OK)
                    .body(mapper.writeValueAsString(GET_TARGET_NOT_EXIST_MESSAGE))
            }
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message ?: UNKNOWN_ERROR_MESSAGE)
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
    ): ResponseEntity<String> {
        // CreateBookRequest内のアノテーションによるバリデーションチェックでエラーが発生した場合の処理
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(errorMessageList))
        }
        try {
            // バリデーション処理
            validate.validCreateBook(request)
            // 著者の存在チェック
            val authorIdList = request.authorIdList.let {
                // authorIdListはnullチェック済みのためnullになることはない
                request.authorIdList
            } ?: throw IllegalStateException("authorIdListの値が不正です。")
            for (authorId in authorIdList) {
                // 入力された著者IDに対応する著者がいない場合はエラーとして処理
                authorService.getAuthor(authorId.toInt())
                    ?: throw IllegalStateException("入力された著者IDに該当する著者は存在しません。")
            }
            // リクエストを詰め替える
            val book = convertCreateBook(request)
            // 書籍登録処理
            val bookId = bookService.createBook(book)
            // レスポンスに詰め替える
            val response = convertCreateBookResponse(bookId)
            // JSONで返す
            val mapper = ObjectMapper()
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response))
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message ?: UNKNOWN_ERROR_MESSAGE)
        }
    }

    /**
     * 書籍情報を更新する
     *
     * @args request updateBookのリクエスト
     * @return 書籍IDのJSON
     */
    @PostMapping("/updateBook")
    fun updateBookController(
        @Validated @RequestBody request: UpdateBookRequest,
        bindingResult: BindingResult
    ): ResponseEntity<String> {
        // UpdateBookRequest内のアノテーションによるバリデーションチェックでエラーが発生した場合の処理
        if (bindingResult.hasErrors()) {
            val errorMessageList = mutableListOf<String>()
            for (bindingError in bindingResult.fieldErrors) {
                errorMessageList.add(bindingError.defaultMessage?.let {
                    bindingError.defaultMessage
                } ?: UNKNOWN_ERROR_MESSAGE)
            }
            val mapper = ObjectMapper()
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(errorMessageList))
        }
        try {
            // バリデーション処理
            validate.validUpdateBook(request)
            // 著者の存在チェック
            val authorIdList = request.authorIdList.let {
                // authorIdListはnullチェック済みのためnullになることはない
                request.authorIdList
            } ?: throw IllegalStateException("authorIdListの値が不正です。")
            for (authorId in authorIdList) {
                // 入力された著者IDに対応する著者がいない場合はエラーとして処理
                authorService.getAuthor(authorId.toInt())
                    ?: throw IllegalStateException("入力された著者IDに該当する著者は存在しません。")
            }
            // リクエストを詰め替える
            val book = convertUpdateBook(request)
            // 著者更新処理
            val bookId = bookService.updateBook(book)
            val mapper = ObjectMapper()
            if (bookId == null) {
                // 更新対象が存在しない場合はメッセージを返す
                return ResponseEntity.status(HttpStatus.OK)
                    .body(mapper.writeValueAsString(UPDATE_TARGET_NOT_EXIST_MESSAGE))
            }
            // レスポンスに詰め替える
            val response = convertUpdateBookResponse(bookId)
            // JSONで返す
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(response))
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message ?: UNKNOWN_ERROR_MESSAGE)
        }
    }

    /**
     * 書籍取得処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args book 書籍Entity
     * @return 書籍取得処理のレスポンスオブジェクト
     */
    private fun convertGetBookResponse(book: Book): GetBookResponse {
        return GetBookResponse(
            bookId = book.id?.let {
                // bookIdは主キーのためここでnullが入ることはない
                book.id
            } ?: throw IllegalStateException("bookIdの値が不正です。"),
            authorIdList = book.authorIdList?.let {
                book.authorIdList.map { authorId ->
                    authorId
                }
            } ?: throw IllegalStateException("authorIdListの値が不正です。"),
            title = book.title?.let {
                // titleはDBの必須項目となっているためここでnullが入ることはない
                book.title
            } ?: throw IllegalStateException("titleの値が不正です。"),
            price = book.price?.let {
                // priceはDBの必須項目となっているためここでnullが入ることはない
                book.price.toInt()
            } ?: throw IllegalStateException("priceの値が不正です。"),
            publicationStatus = book.publicationStatus?.let {
                // publicationStatusはDBの必須項目となっているためここでnullが入ることはない
                book.publicationStatus.code
            } ?: throw IllegalStateException("publicationStatusの値が不正です。"),
        )
    }

    /**
     * 書籍登録処理のリクエストを書籍Entityに詰め替える
     *
     * @args request 書籍登録処理のリクエスト
     * @return 書籍Entity
     */
    private fun convertCreateBook(request: CreateBookRequest): Book {
        return Book(
            id = null,
            authorIdList = request.authorIdList?.let {
                // authorIdListはnullチェック済みのためnullになることはない
                request.authorIdList.map { authorId ->
                    authorId.toInt()
                }
            } ?: throw IllegalStateException("authorIdListの値が不正です"),
            title = request.title,
            price = request.price?.let {
                // priceはnullチェック済みのためnullになることはない
                request.price.toDouble()
            } ?: throw IllegalStateException("priceの値が不正です"),
            publicationStatus = request.publicationStatus?.let {
                // publicationStatusはnullチェック済みのためnullになることはない
                PublicationStatus.getPublicationStatus(request.publicationStatus)
            } ?: throw IllegalStateException("publicationStatusの値が不正です"),
            operator = request.operator,
            // 登録処理時には削除フラグは「0：未削除」をいれる
            deleteFlg = DELETE_FLG_ZERO
        )
    }

    /**
     * 書籍更新処理のリクエストを書籍Entityに詰め替える
     *
     * @args request 書籍更新処理のリクエスト
     * @return 書籍Entity
     */
    private fun convertUpdateBook(request: UpdateBookRequest): Book {
        return Book(
            id = request.bookId?.let {
                // bookIdはnullチェック済みのためnullになることはない
                request.bookId.toInt()
            } ?: throw IllegalStateException("bookIdの値が不正です"),
            authorIdList = request.authorIdList?.let {
                request.authorIdList.map { authorId ->
                    authorId.toInt()
                }
            },
            title = request.title,
            price = if (request.price.isNullOrBlank()) {
                // priceがnullまたは未入力の場合は更新対象外のためnullとする
                null
            } else {
                // priceが入力されている場合は更新対象のためDouble型に変換して渡す
                request.price.toDouble()
            },
            publicationStatus = request.publicationStatus?.let {
                PublicationStatus.getPublicationStatus(request.publicationStatus)
            },
            operator = request.operator,
            deleteFlg = request.deleteFlg
        )
    }

    /**
     * 書籍登録処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args bookId 書籍ID
     * @return 書籍登録処理のレスポンスオブジェクト
     */
    private fun convertCreateBookResponse(bookId: Int): CreateBookResponse {
        return CreateBookResponse(
            bookId = bookId
        )
    }

    /**
     * 書籍更新処理の結果をレスポンスオブジェクトに詰め替える
     *
     * @args bookId 書籍ID
     * @return 書籍更新処理のレスポンスオブジェクト
     */
    private fun convertUpdateBookResponse(bookId: Int): UpdateBookResponse {
        return UpdateBookResponse(
            bookId = bookId
        )
    }
}
