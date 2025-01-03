package com.bookmanagementsystem.validator

import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class AuthorValidator(
    val commonValidator: CommonValidator
) {

    /**
     * 著者取得処理のリクエストに対するバリデーション
     * @args request 著者取得処理のリクエスト
     */
    fun validGetAuthor(request: GetAuthorRequest) {
        // リクエスト値にauthorIdを定義しなかった場合にアノテーションでチェックできないためここで必須チェックを実施
        checkRequiredId(request.authorId)
    }

    /**
     * 著者登録処理のリクエストに対するバリデーション
     * @args request 著者登録処理のリクエスト
     */
    fun validCreateAuthor(request: CreateAuthorRequest) {
        // リクエスト値に各項目を定義しなかった場合にアノテーションでチェックできないためここで必須チェックを実施
        checkRequiredName(request.authorName)
        checkRequiredBirthday(request.birthday)
        // 過去日チェック
        checkPastDateBirthday(request.birthday)
        // 操作者チェック
        commonValidator.checkOperator(request.operator)
    }

    /**
     * 著者更新処理のリクエストに対するバリデーション
     * @args request 著者更新処理のリクエスト
     */
    fun validUpdateAuthor(request: UpdateAuthorRequest) {
        // リクエスト値にauthorIdを定義しなかった場合にアノテーションでチェックできないためここで必須チェックを実施
        checkRequiredId(request.authorId)
        // 過去日チェック
        checkPastDateBirthday(request.birthday)
        // 操作者チェック
        commonValidator.checkOperator(request.operator)
    }

    /**
     * 著者IDの必須チェック
     * @args authorId 著者ID
     */
    private fun checkRequiredId(authorId: String?) {
        // 必須チェック
        if (authorId.isNullOrBlank()) {
            throw NullPointerException("authorIdを入力してください。")
        }
    }

    /**
     * 著者名の必須チェック
     * @args authorName 著者名
     */
    private fun checkRequiredName(authorName: String?) {
        // 桁数チェック(DB定義：256桁)
        if (authorName.isNullOrBlank()) {
            throw IllegalStateException("authorNameを入力してください。")
        }
    }

    /**
     * 誕生日の必須チェック
     * @args birthday 誕生日
     */
    private fun checkRequiredBirthday(birthday: LocalDate?) {
        // 未来日チェック
        if (birthday == null) {
            throw IllegalStateException("birthdayを入力してください")
        }
    }

    /**
     * 誕生日の過去日チェック
     * @args birthday 誕生日
     */
    private fun checkPastDateBirthday(birthday: LocalDate?) {
        // 未来日チェック
        if (birthday != null) {
            if (birthday.isAfter(LocalDate.now())) {
                throw IllegalStateException("birthdayは過去日を設定してください。")
            }
        }
    }
}
