package com.bookmanagementsystem.validator


import com.bookmanagementsystem.enumkt.PublicationStatus
import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookFromAuthorRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import com.bookmanagementsystem.request.book.UpdateBookRequest
import org.springframework.stereotype.Component

@Component
class BookValidator {

    /**
     * 書籍取得処理のバリデーション
     *
     * @param request 書籍取得処理のリクエスト
     */
    fun validGetBook(request: GetBookRequest) {
        checkRequiredBookId(request.bookId)
    }

    /**
     * 著者IDから書籍を取得する処理のバリデーション
     *
     * @param request 書籍取得処理のリクエスト
     */
    fun validGetBookFromAuthor(request: GetBookFromAuthorRequest) {
        checkRequiredAuthorId(request.authorId)
    }

    /**
     * 書籍登録処理のバリデーション
     *
     * @param request 書籍登録処理のリクエスト
     */
    fun validCreateBook(request: CreateBookRequest) {
        checkRequiredAuthorIdList(request.authorIdList)
        checkRequiredName(request.title)
        checkRequiredPrice(request.price)
        checkRequiredPublicationStatus(request.publicationStatus)
        checkValidityPublicationStatus(request.publicationStatus)
    }

    /**
     * 書籍更新処理のバリデーション
     *
     * @param request 書籍更新処理のリクエスト
     */
    fun validUpdateBook(request: UpdateBookRequest) {
        checkRequiredBookId(request.bookId)
        checkValidityPublicationStatus(request.publicationStatus)
    }

    /**
     * 書籍IDの必須チェック
     *
     * @param bookId 書籍ID
     */
    private fun checkRequiredBookId(bookId: Int?) {
        // 必須チェック
        if (bookId == null) {
            throw NullPointerException("bookIdを入力してください。")
        }
    }

    /**
     * 著者IDの必須チェック
     *
     * @param authorId 著者ID
     */
    private fun checkRequiredAuthorId(authorId: Int?) {
        // 必須チェック
        if (authorId == null) {
            throw NullPointerException("authorIdを入力してください。")
        }
    }

    /**
     * 著者IDリストの必須チェック
     *
     * @param authorIdList 著者IDリスト
     */
    private fun checkRequiredAuthorIdList(authorIdList: List<Int>?) {
        // 必須チェック
        if (authorIdList.isNullOrEmpty()) {
            throw NullPointerException("authorIdListを入力してください。")
        }
    }

    /**
     * タイトルの必須チェック
     *
     * @param title タイトル
     */
    private fun checkRequiredName(title: String?) {
        // 必須チェック
        if (title.isNullOrBlank()) {
            throw NullPointerException("titleを入力してください。")
        }
    }

    /**
     * 価格の必須チェック
     *
     * @param price 価格
     */
    private fun checkRequiredPrice(price: Double?) {
        // 必須チェック
        if (price == null) {
            throw NullPointerException("priceを入力してください。")
        }
    }

    /**
     * 出版状況の必須チェック
     *
     * @param publicationStatus 出版状況
     */
    private fun checkRequiredPublicationStatus(publicationStatus: String?) {
        // 必須チェック
        if (publicationStatus.isNullOrBlank()) {
            throw NullPointerException("publicationStatusを入力してください。")
        }
    }

    /**
     * 出版状況の妥当性チェック
     *
     * @param publicationStatus 出版状況
     */
    private fun checkValidityPublicationStatus(publicationStatus: String?) {
        // 妥当性チェック
        if (publicationStatus?.let { PublicationStatus.getPublicationStatus(it) } == PublicationStatus.NONE) {
            throw IllegalStateException("publicationStatusの入力値が不正です。")
        }
    }
}
