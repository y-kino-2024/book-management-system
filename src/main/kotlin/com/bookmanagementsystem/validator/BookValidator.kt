package com.bookmanagementsystem.validator

import com.bookmanagementsystem.enumkt.PublicationStatus
import com.bookmanagementsystem.request.book.CreateBookRequest
import com.bookmanagementsystem.request.book.GetBookFromAuthorRequest
import com.bookmanagementsystem.request.book.GetBookRequest
import com.bookmanagementsystem.request.book.UpdateBookRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 書籍バリデーションクラス
 */
@Component
class BookValidator(
    @Autowired
    val commonValidator: CommonValidator
) {

    /**
     * 書籍取得処理のバリデーション
     *
     * @args request 書籍取得処理のリクエスト
     */
    fun validGetBook(request: GetBookRequest) {
        // リクエスト値にbookIdを定義しなかった場合にアノテーションで必須チェックできないためここで必須チェックを実施
        checkRequiredBookId(request.bookId)
    }

    /**
     * 著者IDから書籍を取得する処理のバリデーション
     *
     * @args request 著者IDから書籍取得処理のリクエスト
     */
    fun validGetBookFromAuthor(request: GetBookFromAuthorRequest) {
        // リクエスト値にauthorIdを定義しなかった場合にアノテーションで必須チェックできないためここで必須チェックを実施
        checkRequiredAuthorId(request.authorId)
    }

    /**
     * 書籍登録処理のバリデーション
     *
     * @args request 書籍登録処理のリクエスト
     */
    fun validCreateBook(request: CreateBookRequest) {
        // リクエスト値に項目ごと定義しなかった場合、アノテーションで必須チェックができないため各必須項目の必須チェックを実施
        // 著者IDリストの必須チェック
        checkRequiredAuthorIdList(request.authorIdList)
        // 著者IDリストの数値チェック
        checkNumberAuthorIdList(request.authorIdList)
        // タイトルの必須チェック
        checkRequiredTitle(request.title)
        // 価格の必須チェック
        checkRequiredPrice(request.price)
        // 出版状況の必須チェック
        checkRequiredPublicationStatus(request.publicationStatus)
        // 出版状況の妥当性チェック
        checkValidityPublicationStatus(request.publicationStatus)
        // 操作者チェック
        commonValidator.checkOperator(request.operator)
    }

    /**
     * 書籍更新処理のバリデーション
     *
     * @args request 書籍更新処理のリクエスト
     */
    fun validUpdateBook(request: UpdateBookRequest) {
        // リクエスト値にbookIdを定義しなかった場合、アノテーションで必須チェックができないためここで必須チェックを実施
        checkRequiredBookId(request.bookId)
        // 著者IDリストの数値チェック
        checkNumberAuthorIdList(request.authorIdList)
        // 出版状況の妥当性チェック
        checkValidityPublicationStatus(request.publicationStatus)
        // 操作者チェック
        commonValidator.checkOperator(request.operator)
    }

    /**
     * 書籍IDの必須チェック
     *
     * @args bookId 書籍ID
     */
    private fun checkRequiredBookId(bookId: String?) {
        // 必須チェック
        if (bookId.isNullOrBlank()) {
            throw NullPointerException("bookIdを入力してください。")
        }
    }

    /**
     * 著者IDの必須チェック
     *
     * @args authorId 著者ID
     */
    private fun checkRequiredAuthorId(authorId: String?) {
        // 必須チェック
        if (authorId.isNullOrBlank()) {
            throw NullPointerException("authorIdを入力してください。")
        }
    }

    /**
     * 著者IDリストの必須チェック
     *
     * @args authorIdList 著者IDリスト
     */
    private fun checkRequiredAuthorIdList(authorIdList: List<String>?) {
        // 必須チェック
        if (authorIdList.isNullOrEmpty()) {
            throw NullPointerException("authorIdListを入力してください。")
        }
    }

    /**
     * 著者IDリストの中身が数値かチェック
     *
     * @args authorIdList 著者IDリスト
     */
    private fun checkNumberAuthorIdList(authorIdList: List<String>?) {
        // 数値チェック
        if (!authorIdList.isNullOrEmpty()) {
            val numericRegexp = Regex("^\\d{1,8}\$")
            for (authorId in authorIdList) {
                if (!authorId.matches(numericRegexp)) {
                    throw IllegalStateException("authorIdは8桁以下の数値で入力してください。")
                }
            }
        }
    }

    /**
     * タイトルの必須チェック
     *
     * @args title タイトル
     */
    private fun checkRequiredTitle(title: String?) {
        // 必須チェック
        if (title.isNullOrBlank()) {
            throw NullPointerException("titleを入力してください。")
        }
    }

    /**
     * 価格の必須チェック
     *
     * @args price 価格
     */
    private fun checkRequiredPrice(price: String?) {
        // 必須チェック
        if (price.isNullOrBlank()) {
            throw NullPointerException("priceを入力してください。")
        }
    }

    /**
     * 出版状況の必須チェック
     *
     * @args publicationStatus 出版状況
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
     * @args publicationStatus 出版状況
     */
    private fun checkValidityPublicationStatus(publicationStatus: String?) {
        // 妥当性チェック
        if (!publicationStatus.isNullOrBlank()) {
            // Enum定義されている値か確認
            if (!PublicationStatus.contains(publicationStatus)) {
                throw IllegalStateException("publicationStatusの入力値が不正です。")
            }
        }
    }
}
