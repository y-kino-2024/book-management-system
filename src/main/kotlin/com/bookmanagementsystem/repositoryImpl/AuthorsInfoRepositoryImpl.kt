package com.bookmanagementsystem.repositoryImpl

import bookmanagementsystem.jooq.quo_assignment.Sequences
import bookmanagementsystem.jooq.quo_assignment.tables.AuthorsInfo.AUTHORS_INFO
import com.bookmanagementsystem.dto.AuthorsInfoDto
import com.bookmanagementsystem.repository.AuthorsInfoRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

/**
 * Authors_infoリポジトリの実装クラス
 */
@Repository
class AuthorsInfoRepositoryImpl(
    private val dslContext: DSLContext
) : AuthorsInfoRepository {

    /**
     * 著者情報をDBから取得する
     * @args authorId 著者ID
     * @return 著者情報
     */
    @Override
    @Transactional
    override fun getAuthor(authorId: String): AuthorsInfoDto? {
        println("impl:$authorId")
        // クエリを生成・実行する
        val result = this.dslContext.select()
            .from(AUTHORS_INFO)
            .where(AUTHORS_INFO.ID.eq(authorId.toInt()))
            .fetchOne()
        println("impl:$result")
        // 取得した内容をDtoに詰め替える
        val dto = if (result != null) {
            AuthorsInfoDto(
                id = result.getValue(AUTHORS_INFO.ID)!!,
                authorName = result.getValue(AUTHORS_INFO.AUTHOR_NAME)!!,
                birthday = result.getValue(AUTHORS_INFO.BIRTHDAY)!!,
                createdBy = result.getValue(AUTHORS_INFO.CREATED_BY)!!,
                createdAt = result.getValue(AUTHORS_INFO.CREATED_AT)!!,
                updatedBy = result.getValue(AUTHORS_INFO.UPDATED_BY)!!,
                updatedAt = result.getValue(AUTHORS_INFO.UPDATED_AT)!!,
                deleteFlg = result.getValue(AUTHORS_INFO.DELETE_FLG)!!,
            )
        } else {
            null
        }
        // Dtoを返す
        println("impl:$dto")
        return dto

        // TODO 動確用返り値
        /*
        return AuthorsInfoDto(
            id = 99999999,
            authorName = "test",
            birthday = LocalDate.now(),
            createdBy = "test",
            createdAt = LocalDateTime.now(),
            updatedBy = "test",
            updatedAt = LocalDateTime.now(),
            deleteFlg = "0",
        )
        */
        // エラー処理(SQLException)
        // エラー処理(Exception)　詰め替え時のエラー考慮
    }

    /**
     * 著者情報をテーブルに登録する
     * @args author 著者情報
     * @return 著者ID
     */
    @Override
    //@Transactional
    override fun createAuthor(author: AuthorsInfoDto): Int {
        // TODO 処理内容
        try {
            // トランザクションを張る
            //dslContext.transaction { c ->
            // シーケンスを取得する

            // クエリを生成・実行する
            /*
            DSL.using(c)
                .insertInto(
                    AUTHORS_INFO,
                    AUTHORS_INFO.ID,
                    AUTHORS_INFO.AUTHOR_NAME,
                    AUTHORS_INFO.BIRTHDAY,
                    AUTHORS_INFO.CREATED_BY,
                    AUTHORS_INFO.CREATED_AT,
                    AUTHORS_INFO.UPDATED_BY,
                    AUTHORS_INFO.UPDATED_AT,
                    AUTHORS_INFO.DELETE_FLG,
                )
                .values(
                    authorIdSeq, author.authorName, author.birthday, author.createdBy, author.createdAt,
                    author.updatedBy, author.updatedAt, author.deleteFlg
                )
                .execute()
            */

            val result = dslContext
                .insertInto(
                    AUTHORS_INFO,
                    //AUTHORS_INFO.ID,
                    AUTHORS_INFO.AUTHOR_NAME,
                    AUTHORS_INFO.BIRTHDAY,
                    AUTHORS_INFO.CREATED_BY,
                    AUTHORS_INFO.CREATED_AT,
                    AUTHORS_INFO.UPDATED_BY,
                    AUTHORS_INFO.UPDATED_AT,
                    AUTHORS_INFO.DELETE_FLG,
                )
                .values(
                    //author.id,
                    author.authorName, author.birthday, author.createdBy, author.createdAt,
                    author.updatedBy, author.updatedAt, author.deleteFlg
                )
                .execute()

//                this.dslContext.newRecord(AUTHORS_INFO).also {
//                    it.id = authorId.toInt()
//                    it.authorName = author.authorName
//                    it.birthday = author.birthday
//                    it.createdBy = author.createdBy
//                    it.createdAt = author.createdAt
//                    it.updatedBy = author.updatedBy
//                    it.updatedAt = author.updatedAt
//                    it.deleteFlg = author.deleteFlg
//                    it.store()
//                }
//                return authorId
            //result.getValue(AUTHORS_INFO.ID).toString()
            // }
            // 著者IDを返す
            return result
            // TODO 動確用返り値
            //return "createTest"
            // エラー処理(SQLException)
        } catch (e: SQLException) {
            throw SQLException("DB処理実施時にエラーが発生しました。")
            // エラー処理(Exception)　詰め替え時のエラー考慮
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    /**
     * 著者情報を更新する
     * @args author 著者情報
     * @return 著者ID
     */
    @Override
    override fun updateAuthor(author: AuthorsInfoDto): Int {
        // TODO 処理内容
        // クエリを生成する
        // クエリを実行する
        // 著者IDを返す

        // TODO 動確用返り値
        return 0
    }

    /**
     * 著者IDを発番する
     */
    @Override
    override fun nextAuthorIdSequence(): Int {
        return dslContext.nextval(Sequences.AUTHOR_ID_SEQ).toInt()
        //return dslContext.select(s).fetchOne(s);
    }
}
