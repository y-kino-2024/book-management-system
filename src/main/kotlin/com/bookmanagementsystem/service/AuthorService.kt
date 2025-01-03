package com.bookmanagementsystem.service

import com.bookmanagementsystem.controller.AuthorController.Companion.DELETE_FLG_ZERO
import com.bookmanagementsystem.dto.AuthorsInfoDto
import com.bookmanagementsystem.entity.Author
import com.bookmanagementsystem.repositoryImpl.AuthorsInfoRepositoryImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 著者のService
 */
@Service
class AuthorService(
    private val authorsInfoRepositoryImpl: AuthorsInfoRepositoryImpl
) {

    /**
     * 著者IDから著者を取得する
     *
     * @args authorId 著者ID
     * @return 著者
     */
    @Transactional
    fun getAuthor(authorId: String): Author? {
        // 取得処理
        val result = authorsInfoRepositoryImpl.getAuthor(authorId)
        // 取得結果による分岐
        return if (result != null) {
            // 取得できた場合
            convertAuthor(result)
        } else {
            // 取得結果が0件の場合
            null
        }
    }

    /**
     * 著者の情報を登録する
     *
     * @args author 著者
     * @return 登録した著者ID
     */
    @Transactional
    fun createAuthor(author: Author): String {
        // チェック処理

        // 処理日時を取得
        val processingDatetime = getProcessingDateTime()
        // Dtoに詰め替える
        val authorDto = convertAuthorsInfoDto(
            author = author,
            processingDatetime = processingDatetime
        )
        // 登録処理
        val result = authorsInfoRepositoryImpl.createAuthor(authorDto)
        // 登録した著者IDを取得
        val authorId = authorsInfoRepositoryImpl.currentAuthorIdSequence()
        if (result > 0) {
            // 処理された場合
            return authorId.toString()
        } else {
            // 処理されなかった場合はエラー
            throw Exception("著者情報の登録に失敗しました。")
        }
    }

    /**
     * 著者の情報を更新する
     *
     * @args author 著者
     * @return 更新対象の著者ID
     */
    @Transactional
    fun updateAuthor(author: Author): String {
        // チェック処理

        // 取得処理
        val targetAuthor = authorsInfoRepositoryImpl.getAuthor(author.id.toString())
            ?: throw IllegalStateException("更新対象が存在しません。")
        // 処理日時を取得
        val processingDatetime = getProcessingDateTime()
        // Dtoに詰め替える
        val authorDto = convertAuthorsInfoDto(
            author = author,
            currentAuthor = targetAuthor,
            processingDatetime = processingDatetime
        )
        // 更新処理
        val result = authorsInfoRepositoryImpl.updateAuthor(authorDto)
        if (result > 0) {
            // 処理された場合
            return author.id.toString()
        } else {
            // 処理されなかった場合はエラー
            throw Exception("著者情報の更新に失敗しました。")
        }
    }

    /**
     * 処理日時を取得する
     *
     * @return 処理日時
     */
    private fun getProcessingDateTime(): LocalDateTime {
        val now = LocalDateTime.now()
        // ミリ秒まで表示させるように整形
        val processingDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss.SSS")
        val processingDateTime = LocalDateTime.parse(now.format(processingDateFormat), processingDateFormat)
        return processingDateTime
    }

    /**
     * 著者のEntityを著者のDtoに変換する
     *
     * @args author 著者Entity
     * @args authorId 著者ID
     * @args processingDatetime 処理日時
     * @return 著者Dto
     */
    private fun convertAuthor(
        authorsInfoDto: AuthorsInfoDto,
    ): Author {
        return Author(
            id = authorsInfoDto.id,
            authorName = authorsInfoDto.authorName,
            birthday = authorsInfoDto.birthday,
            operator = authorsInfoDto.updatedBy,
            deleteFlg = authorsInfoDto.deleteFlg
        )
    }

    /**
     * 著者のEntityを著者のDtoに変換する
     *
     * @args author 著者Entity
     * @args processingDatetime 処理日時
     * @return 著者Dto
     */
    private fun convertAuthorsInfoDto(
        author: Author,
        processingDatetime: LocalDateTime
    ): AuthorsInfoDto {
        return AuthorsInfoDto(
            id = null,
            authorName = author.authorName?.let {
                author.authorName
            } ?: throw IllegalStateException("著者名の値が不正です"),
            birthday = author.birthday?.let {
                author.birthday
            } ?: throw IllegalStateException("誕生日の値が不正です"),
            createdBy = author.operator?.let {
                author.operator
            } ?: throw IllegalStateException("操作者の値が不正です"),
            createdAt = processingDatetime,
            updatedBy = author.operator,
            updatedAt = processingDatetime,
            deleteFlg = DELETE_FLG_ZERO,
        )
    }

    /**
     * 著者のEntityを著者のDtoに変換する
     *
     * @args author 著者Entity
     * @args processingDatetime 処理日時
     * @return 著者Dto
     */
    private fun convertAuthorsInfoDto(
        author: Author,
        currentAuthor: AuthorsInfoDto,
        processingDatetime: LocalDateTime
    ): AuthorsInfoDto {
        return AuthorsInfoDto(
            id = author.id,
            authorName = author.authorName ?: currentAuthor.authorName,
            birthday = author.birthday ?: currentAuthor.birthday,
            createdBy = currentAuthor.createdBy,
            createdAt = currentAuthor.createdAt,
            updatedBy = author.operator?.let {
                author.operator
            } ?: throw IllegalStateException("操作者の値が不正です"),
            updatedAt = processingDatetime,
            deleteFlg = author.deleteFlg ?: currentAuthor.deleteFlg,
        )
    }
}
