package com.bookmanagementsystem.validator


import com.bookmanagementsystem.enum.PublicationStatus
import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import com.bookmanagementsystem.request.book.UpdateBookRequest
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BookValidator {

    companion object {
        // 書籍IDの最大桁数
        const val BOOK_ID_MAX = 8

        // 書籍名の最大文字数
        const val TITLE_MAX = 256

        // 価格の最小値
        const val PRICE_MIN = 0
    }

    /**
     * 書籍取得処理のバリデーション
     * @param request 書籍取得処理のリクエスト
     */
    fun validGetBook(request: GetBookRequest) {
        checkId(request.bookId)
    }

    /**
     * 書籍登録処理のバリデーション
     * @param request 書籍登録処理のリクエスト
     */
    fun validCreateBook(request: CreateBookRequest) {
        checkName(request.title)
        checkPrice(request.price)
        checkPublicationStatus(request.publicationStatus)
    }

    /**
     * 書籍更新処理のバリデーション
     * @param request 書籍更新処理のリクエスト
     */
    fun validUpdateBook(request: UpdateBookRequest) {
        checkId(request.bookId)
        checkName(request.title)
        checkPrice(request.price)
        checkPublicationStatus(request.publicationStatus)
    }

    /**
     * 書籍IDのチェック
     * @param bookId 書籍ID
     */
    private fun checkId(bookId: String?) {
        // 必須チェック
        if (bookId.isNullOrBlank()) {
            throw NullPointerException("書籍IDを入力してください。")
        }
        // 桁数チェック(DB定義：8桁)
        if (bookId.length > BOOK_ID_MAX) {
            throw IllegalStateException("書籍IDは8桁以内で入力してください。")
        }
    }

    /**
     * タイトルのチェック
     * @param title タイトル
     */
    private fun checkName(title: String?) {
        // 必須チェック
        if (title.isNullOrBlank()) {
            throw NullPointerException("タイトルを入力してください。")
        }
        // 桁数チェック(DB定義：256桁)
        if (title.length > TITLE_MAX) {
            throw IllegalStateException("タイトルは256文字以内で入力してください。")
        }
    }

    /**
     * 価格のチェック
     * @param price 価格
     */
    private fun checkPrice(price: Int?) {
        // 必須チェック
        if (price == null) {
            throw NullPointerException("価格を入力してください。")
        }
        // 桁数チェック(DB定義：256桁)
        if (price < PRICE_MIN) {
            throw IllegalStateException("価格は0以上で入力してください。")
        }
    }

    /**
     * 出版状況のチェック
     * @param publicationStatus 出版状況
     */
    private fun checkPublicationStatus(publicationStatus: String?) {
        // 必須チェック
        if (publicationStatus.isNullOrBlank()) {
            throw NullPointerException("出版状況を入力してください。")
        }
        // 妥当性チェック
        if (PublicationStatus.valueOf(publicationStatus) == PublicationStatus.NONE) {
            throw IllegalStateException("出版状況の入力値が不正です。")
        }
    }
}
