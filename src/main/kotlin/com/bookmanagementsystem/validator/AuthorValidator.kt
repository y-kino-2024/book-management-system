package com.bookmanagementsystem.validator

import com.bookmanagementsystem.request.author.CreateAuthorRequest
import com.bookmanagementsystem.request.author.GetAuthorRequest
import com.bookmanagementsystem.request.author.UpdateAuthorRequest
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.time.LocalDate

@SpringBootApplication
class AuthorValidator (
    val commonValidator:CommonValidator
){

    companion object {
        // 著者IDの最大桁数
        const val AUTHOR_ID_MAX = 8

        // 著者名の最大文字数
        const val AUTHOR_NAME_MAX = 256
    }

    /**
     * 著者取得処理のバリデーション
     * @args request 著者取得処理のリクエスト
     */
    fun validGetAuthor(request: GetAuthorRequest) {
        checkId(request.authorId)
    }

    /**
     * 著者登録処理のバリデーション
     * @args request 著者登録処理のリクエスト
     */
    fun validCreateAuthor(request: CreateAuthorRequest) {
        checkName(request.authorName)
        checkBirthday(request.birthday)
        commonValidator.validCreate(request.operator)
    }

    /**
     * 著者更新処理のバリデーション
     * @args request 著者更新処理のリクエスト
     */
    fun validUpdateAuthor(request: UpdateAuthorRequest) {
        checkId(request.authorId)
        checkName(request.authorName)
        checkBirthday(request.birthday)
        commonValidator.validUpdate(request.operator)
    }

    /**
     * 著者IDのチェック
     * @args authorId 著者ID
     */
    private fun checkId(authorId: String?) {
        // 必須チェック
        if (authorId.isNullOrBlank()) {
            throw NullPointerException("著者IDが未入力です。")
        }
        // 桁数チェック(DB定義：8桁)
        if (authorId.length > AUTHOR_ID_MAX) {
            throw IllegalStateException("著者IDは8桁以内で入力してください。")
        }
    }

    /**
     * 著者名のチェック
     * @args authorName 著者名
     */
    private fun checkName(authorName: String?) {
        // 必須チェック
        if (authorName.isNullOrBlank()) {
            throw NullPointerException("著者名が未入力です。")
        }
        // 桁数チェック(DB定義：256桁)
        if (authorName.length > AUTHOR_NAME_MAX) {
            throw IllegalStateException("著者名は256文字以内で入力してください。")
        }
    }

    /**
     * 誕生日のチェック
     * @args birthday 誕生日
     */
    private fun checkBirthday(birthday: LocalDate?) {
        // 必須チェック
        if (birthday == null) {
            throw NullPointerException("誕生日が未入力です。")
        }
        // 未来日チェック
        if (birthday.isAfter(LocalDate.now())) {
            throw IllegalStateException("誕生日は過去日を設定してください。")
        }
    }
}
