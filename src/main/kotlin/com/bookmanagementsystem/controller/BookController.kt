package com.bookmanagementsystem.controller

import com.bookmanagementsystem.entity.Book
import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import com.bookmanagementsystem.request.book.UpdateBookRequest
import com.bookmanagementsystem.response.book.GetBookResponse
import com.bookmanagementsystem.service.BookService
import com.bookmanagementsystem.validator.BookValidator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 書籍のController
 */
@RestController
class BookController(
    val validate: BookValidator,
    val service: BookService
) {

    /**
     * 書籍情報を取得する
     *
     * @args request getBookのリクエスト
     * @return 書籍情報のJSON
     */
    @GetMapping("/getBook")
    fun getBookController(request: GetBookRequest): String {
        try {
            // バリデーション処理
            validate.validGetBook(request)
            // リクエストを詰め替える
            // リクエスト詰め替え不要
            // 書籍取得処理
            //val author = model.getBook(authorId = request.authorId)
            // レスポンスに詰め替える
            //val response = convertGetBookResponse(author)
            // TODO JSONで返す
            //return Json.encodeToString(response)
            return "Test"
            // 完了
        } catch (e: Exception) {
            return e.message!!
        }
    }

    /**
     * 著者IDから書籍情報を取得する
     *
     * @args request getBookFromAuthorのリクエスト
     * @return 書籍情報のJSON
     */
    @GetMapping("/getBookFromAuthor")
    fun getBookFromAuthorController(request: GetBookRequest): String {
        try {
            // バリデーション処理
            validate.validGetBook(request)
            // リクエストを詰め替える
            // リクエスト詰め替え不要
            // 書籍取得処理
            //val author = model.getBook(authorId = request.authorId)
            // レスポンスに詰め替える
            //val response = convertGetBookResponse(author)
            // TODO JSONで返す
            //return Json.encodeToString(response)
            return "Test"
            // 完了
        } catch (e: Exception) {
            return e.message!!
        }
    }

    /**
     * 書籍情報を登録する
     *
     * @args request createBookのリクエスト
     * @return 書籍IDのJSON
     */
    @PostMapping("/createBook")
    fun createBookController(request: CreateBookRequest): String {
        // バリデーション処理

        // リクエストを詰め替える

        // 書籍登録処理

        // レスポンスに詰め替える

        // TODO JSONで返す
        return "test"
        // 完了
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
            authorId = book.authorId?.let {
                book.authorId
            } ?: throw IllegalStateException("著者IDの値が不正です。"),
            title = book.title?.let {
                book.title
            } ?: throw IllegalStateException("タイトルの値が不正です。"),
            price = book.price?.let {
                book.price.toInt()
            } ?: throw IllegalStateException("価格の値が不正です。"),
            publicationStatus = book.publicationStatus?.let {
                book.publicationStatus.ordinal.toString()
            } ?: throw IllegalStateException("出版状況の値が不正です。"),
        )
    }
}
