package com.bookmanagementsystem.service

import com.bookmanagementsystem.dto.AuthorsInfoDto
import com.bookmanagementsystem.entity.Author
import com.bookmanagementsystem.repositoryImpl.AuthorsInfoRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 著者のService
 */
@SpringBootApplication
class AuthorService (
    private val authorsInfoRepositoryImpl: AuthorsInfoRepositoryImpl
){
    companion object{
        const val DELETE_FLG_ZERO = "0"
    }

    /**
     * 著者IDから著者を取得する
     * @args authorId 著者ID
     * @return 著者
     */
    @Transactional
    fun getAuthor(authorId: String): Author {
        // TODO 処理未実装。エラーださないための仮置き
        // FIXME SQL作成
        //val sql: String = create.select().from(ACTOR).getSQL()
        return Author(
            id = 99999999,
            authorName = "test",
            birthday = LocalDate.now(),
            operator = "testOp"
        )
    }

    /**
     * 著者の情報を登録する
     * @args author 著者
     * @return 著者ID
     */
    @Transactional
    fun createAuthor(author: Author): String {
        // チェック処理

        // 著者IDを発番する
        val authorId = authorsInfoRepositoryImpl.nextAuthorIdSequence()
        // 処理日時のフォーマット作成
        val processingDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss.SSS")
        // 処理日時を取得
        val now = LocalDateTime.now()
        // ミリ秒まで表示させるように整形
        val processingDatetime = LocalDateTime.parse(now.format(processingDateFormat))
        // Dtoに詰め替える
        val authorDto = convertAuthorsInfoDto(
            author=author,
            authorId=authorId,
            processingDatetime=processingDatetime
        )
        // 登録処理
        val result = authorsInfoRepositoryImpl.createAuthor(authorDto)

        if (result > 0) {
            return authorId.toString()
        } else {
            throw Exception("著者の情報登録に失敗しました。")
        }
    }

    /**
     * 著者の情報を更新する
     * @args author 著者
     * @return
     */
    @Transactional
    fun updateAuthor(author: Author) {
        // TODO 処理未実装。
        // TODO 引数の著者Entity内のIDで取得→取得の結果で分岐→処理結果をどう返す？
    }

    // TODO 削除機能はいらない？？
    /*
        必要な機能：書籍と著者の情報をRDBに登録・更新できる機能
    */
    /**
     * 著者を削除する
     * @args authorId 著者ID
     * @return
     */
    /*
    fun deleteAuthor(authorId: String) {
        runApplication<AuthorModel>(*args)
    }
*/

    /**
     * 著者のEntityを著者のDtoに変換する
     * @args author 著者Entity
     * @args authorId 著者ID
     * @args processingDatetime 処理日時
     * @return 著者Dto
     */
    private fun convertAuthorsInfoDto(
        author: Author,
        authorId: Int?,
        processingDatetime:LocalDateTime
        ):AuthorsInfoDto{
        return AuthorsInfoDto(
            id = authorId?.let {
                authorId
            }?:throw IllegalStateException("著者IDが不正です。"),
            authorName = author.authorName,
            birthday = author.birthday,
            createdBy = author.operator,
            createdAt = processingDatetime,
            updatedBy = author.operator,
            updatedAt = processingDatetime,
            deleteFlg = DELETE_FLG_ZERO,
        )
    }
}
