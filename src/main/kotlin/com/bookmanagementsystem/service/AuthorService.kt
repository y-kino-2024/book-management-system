package com.bookmanagementsystem.service

import com.bookmanagementsystem.controller.AuthorController.Companion.DELETE_FLG_ZERO
import com.bookmanagementsystem.dto.AuthorsInfoDto
import com.bookmanagementsystem.entity.Author
import com.bookmanagementsystem.repositoryImpl.AuthorsInfoRepositoryImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException
import java.time.LocalDateTime

/**
 * 著者のService
 */
@Service
class AuthorService(
    private val common: CommonService,
    private val authorsInfoRepositoryImpl: AuthorsInfoRepositoryImpl
) {

    /**
     * 著者IDから著者を取得する
     *
     * @args authorId 著者ID
     * @return 著者Entity
     */
    @Transactional
    fun getAuthor(authorId: Int): Author? {
        try {
            // 取得処理
            val result = authorsInfoRepositoryImpl.fetchAuthor(authorId)
            // 取得結果による分岐
            return if (result != null) {
                // 取得結果を著者Entityに詰め替えて返す
                convertAuthor(result)
            } else {
                // 取得結果が0件の場合はnullを返す
                null
            }
        } catch (se: SQLException) {
            // 取得処理時にエラーが発生した場合はエラーメッセージをそのまま入れて投げる
            throw se
        } catch (e: Exception) {
            // その他要因で処理されなかった場合はExceptionを投げる
            throw Exception("著者情報の更新に失敗しました。")
        }
    }

    /**
     * 著者の情報を登録する
     *
     * @args author 著者Entity
     * @return 登録した著者ID
     */
    @Transactional
    fun createAuthor(author: Author): String {
        try {
            // 処理日時を取得
            val processingDatetime = common.getProcessingDateTime()
            // Dtoに詰め替える
            val authorDto = convertCreateAuthorsInfoDto(
                author = author,
                processingDatetime = processingDatetime
            )
            // 登録処理
            val result = authorsInfoRepositoryImpl.createAuthor(authorDto) ?: throw Exception()
            return result.toString()
        } catch (se: SQLException) {
            // 登録処理時にエラーが発生した場合はエラーメッセージをそのまま入れて投げる
            throw se
        } catch (ise: IllegalStateException) {
            // IllegalStateExceptionが発生した場合はエラーメッセージをそのまま入れて投げる
            throw ise
        } catch (e: Exception) {
            // その他要因で処理されなかった場合はExceptionを投げる
            throw Exception("著者情報の更新に失敗しました。")
        }
    }

    /**
     * 著者の情報を更新する
     *
     * @args author 著者Entity
     * @return 更新対象の著者ID
     */
    @Transactional
    fun updateAuthor(author: Author): String? {
        try {
            // 更新対象の現在の著者情報を取得する
            val targetAuthor = authorsInfoRepositoryImpl.fetchAuthor(
                author.id?.let {
                    // チェックしているためここで著者IDがnullになることはない
                    author.id
                } ?: throw IllegalStateException("authorIdの値が不正です。")
            )
            // 更新対象が存在しない場合はnullを返す
            if (targetAuthor == null) {
                return null
            }
            // 処理日時を取得
            val processingDatetime = common.getProcessingDateTime()
            // Dtoに詰め替える
            val authorDto = convertUpdateAuthorsInfoDto(
                author = author,
                currentAuthor = targetAuthor,
                processingDatetime = processingDatetime
            )
            // 更新処理
            val result = authorsInfoRepositoryImpl.updateAuthor(authorDto)
            if (result > 0) {
                // 処理された場合更新対象の著者IDを返す
                return author.id.toString()
            } else {
                // 処理されなかった場合はエラー
                throw Exception()
            }
        } catch (se: SQLException) {
            // 登録処理時にエラーが発生した場合はエラーメッセージをそのまま入れて投げる
            throw se
        } catch (ise: IllegalStateException) {
            // IllegalStateExceptionが発生した場合はエラーメッセージをそのまま入れて投げる
            throw ise
        } catch (e: Exception) {
            // その他要因で処理されなかった場合はExceptionを投げる
            throw Exception("著者情報の更新に失敗しました。")
        }
    }

    /**
     * 著者のDtoを著者のEntityに変換する
     *
     * @args authorsInfoDto 著者Dto
     * @return 著者Entity
     */
    private fun convertAuthor(
        authorsInfoDto: AuthorsInfoDto,
    ): Author {
        return Author(
            id = authorsInfoDto.id,
            authorName = authorsInfoDto.authorName,
            birthday = authorsInfoDto.birthday,
            // operatorは各レスポンスに設定しない値のためnullとしておく
            operator = null,
            // deleteFlgは各レスポンスに設定しない値のためnullとしておく
            deleteFlg = null
        )
    }

    /**
     * 著者のEntityを登録処理用の著者のDtoに変換する
     *
     * @args author 著者Entity
     * @args processingDatetime 処理日時
     * @return 著者Dto
     */
    private fun convertCreateAuthorsInfoDto(
        author: Author,
        processingDatetime: LocalDateTime
    ): AuthorsInfoDto {
        return AuthorsInfoDto(
            id = null,
            authorName = author.authorName?.let {
                author.authorName
            } ?: throw IllegalStateException("authorNameの値が不正です"),
            birthday = author.birthday?.let {
                author.birthday
            } ?: throw IllegalStateException("birthdayの値が不正です"),
            createdBy = author.operator?.let {
                author.operator
            } ?: throw IllegalStateException("createdByの値が不正です"),
            createdAt = processingDatetime,
            updatedBy = author.operator,
            updatedAt = processingDatetime,
            deleteFlg = DELETE_FLG_ZERO,
        )
    }

    /**
     * 著者のEntityと更新前の著者情報をもとに更新用の著者のDtoに変換する
     *
     * @args author 著者Entity
     * @args currentAuthor 更新前の著者情報
     * @args processingDatetime 処理日時
     * @return 著者Dto
     */
    private fun convertUpdateAuthorsInfoDto(
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
            } ?: throw IllegalStateException("updatedByの値が不正です"),
            updatedAt = processingDatetime,
            deleteFlg = author.deleteFlg ?: currentAuthor.deleteFlg,
        )
    }
}
