package com.bookmanagementsystem.repositoryImpl

import com.bookmanagementsystem.dto.AuthorsInfoDto
import com.bookmanagementsystem.repository.AuthorsInfoRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import quoassignment.jooq.quo_assignment.Tables.AUTHORS_INFO
import java.time.LocalDate

/**
 * Authors_infoリポジトリの実装クラス
 */
@Repository
class AuthorsInfoRepositoryImpl (
    private val dslContext: DSLContext
): AuthorsInfoRepository {

    /**
     * 著者情報をDBから取得する
     * @args authorId 著者ID
     * @return 著者情報
     */
    @Override
    override fun getAuthor(authorId: String): AuthorsInfoDto {
        // TODO 処理内容
        // クエリを生成・実行する
        val result = this.dslContext.select()
            .from(AUTHORS_INFO)
            .where(AUTHORS_INFO.ID.eq(authorId.toInt()))
            .fetchOne()
        // 取得した内容をDtoに詰め替える
        val dto = AuthorsInfoDto(
            id = result?.getValue(AUTHORS_INFO.ID)!!,
            authorName = result.getValue(AUTHORS_INFO.AUTHOR_NAME)!!,
            birthday = result.getValue(AUTHORS_INFO.BIRTHDAY)!!,
            createdBy = result.getValue(AUTHORS_INFO.CREATED_BY)!!,
            createdAt= result.getValue(AUTHORS_INFO.CREATED_AT)!!,
            updatedBy = result.getValue(AUTHORS_INFO.UPDATED_BY)!!,
            updatedAt=result.getValue(AUTHORS_INFO.UPDATED_AT)!!,
            deleteFlg = result.getValue(AUTHORS_INFO.DELETE_FLG)!!,
        )
        // Dtoを返す
        //return dto
        // TODO 動確用返り値
        return AuthorsInfoDto(
            id = 99999999,
            authorName = "test",
            birthday = LocalDate.now(),
            createdBy = "test",
            createdAt=LocalDate.now(),
            updatedBy = "test",
            updatedAt=LocalDate.now(),
            deleteFlg = "0",
        )
    }

    /**
     * 著者情報をテーブルに登録する
     * @args author 著者情報
     * @return 著者ID
     */
    @Override
    override fun createAuthor(author: AuthorsInfoDto): String {
        // TODO 処理内容
        // クエリを生成・実行する
        val result = this.dslContext.newRecord(AUTHORS_INFO).also {
            it.authorName = author.authorName
            it.birthday = author.birthday
            it.createdBy = author.createdBy
            it.createdAt = author.createdAt
            it.updatedBy = author.updatedBy
            it.updatedAt = author.updatedAt
            it.deleteFlg = author.deleteFlg
            it.store()
        }
        // クエリを実行する
        // 著者IDを返す
        return result.getValue(AUTHORS_INFO.ID).toString()
        // TODO 動確用返り値
        //return "createTest"
    }

    /**
     * 著者情報を更新する
     * @args author 著者情報
     * @return 著者ID
     */
    @Override
    override fun updateAuthor(author: AuthorsInfoDto): String {
        // TODO 処理内容
        // クエリを生成する
        // クエリを実行する
        // 著者IDを返す

        // TODO 動確用返り値
        return "updateTest"
    }
}
