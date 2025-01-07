package com.bookmanagementsystem.validator

import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import org.springframework.stereotype.Component
import java.time.LocalDate

/**
 * 著者バリデーションクラス
 */
@Component
class AuthorValidator(
    val commonValidator: CommonValidator
) {

    /**
     * 著者取得処理のリクエストに対するバリデーション
     *
     * @args request 著者取得処理のリクエスト
     */
    fun validGetAuthor(request: GetAuthorRequest) {
        // リクエスト値にauthorIdを定義しなかった場合にアノテーションで必須チェックできないためここで必須チェックを実施
        checkRequiredId(request.authorId)
    }

    /**
     * 著者登録処理のリクエストに対するバリデーション
     *
     * @args request 著者登録処理のリクエスト
     */
    fun validCreateAuthor(request: CreateAuthorRequest) {
        // リクエスト値に項目を定義しなかった場合、アノテーションで必須チェックができないためここで必須チェックを実施
        // 著者名の必須チェック
        checkRequiredName(request.authorName)
        // 誕生日の必須チェック
        checkRequiredBirthday(request.birthday)
        // 誕生日の過去日チェック
        checkPastDateBirthday(request.birthday)
        // 操作者チェック
        commonValidator.checkOperator(request.operator)
    }

    /**
     * 著者更新処理のリクエストに対するバリデーション
     *
     * @args request 著者更新処理のリクエスト
     */
    fun validUpdateAuthor(request: UpdateAuthorRequest) {
        // リクエスト値にauthorIdを定義しなかった場合にアノテーションで必須チェックができないためここで必須チェックを実施
        checkRequiredId(request.authorId)
        // 誕生日の過去日チェック
        checkPastDateBirthday(request.birthday)
        // 操作者チェック
        commonValidator.checkOperator(request.operator)
    }

    /**
     * 著者IDの必須チェック
     *
     * @args authorId 著者ID
     */
    private fun checkRequiredId(authorId: Int?) {
        // 必須チェック
        if (authorId == null) {
            throw NullPointerException("authorIdを入力してください。")
        }
    }

    /**
     * 著者名の必須チェック
     *
     * @args authorName 著者名
     */
    private fun checkRequiredName(authorName: String?) {
        // 必須チェック
        if (authorName.isNullOrBlank()) {
            throw NullPointerException("authorNameを入力してください。")
        }
    }

    /**
     * 誕生日の必須チェック
     *
     * @args birthday 誕生日
     */
    private fun checkRequiredBirthday(birthday: LocalDate?) {
        // 必須チェック
        if (birthday == null) {
            throw NullPointerException("birthdayを入力してください。")
        }
    }

    /**
     * 誕生日の過去日チェック
     *
     * @args birthday 誕生日
     */
    private fun checkPastDateBirthday(birthday: LocalDate?) {
        // 過去日チェック
        if (birthday != null) {
            if (birthday.isAfter(LocalDate.now())) {
                throw IllegalStateException("birthdayは過去日を設定してください。")
            }
        }
    }
}
