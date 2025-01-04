package com.bookmanagementsystem.repositoryImpl

import bookmanagementsystem.jooq.quo_assignment.tables.AuthorsInfo.AUTHORS_INFO
import com.bookmanagementsystem.dto.AuthorsInfoDto
import com.bookmanagementsystem.repository.AuthorsInfoRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
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
     *
     * @args authorId 著者ID
     * @return 著者情報
     */
    @Override
    override fun fetchAuthor(authorId: String): AuthorsInfoDto? {
        try {
            // クエリを生成・実行する
            val result = this.dslContext.select()
                .from(AUTHORS_INFO)
                .where(AUTHORS_INFO.ID.eq(authorId.toInt()))
                .fetchOne()

            val authorDto = if (result != null) {
                // 取得出来た場合はdtoに詰め替える
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
                // 取得結果が0件の場合はnullを返す
                null
            }
            return authorDto
        } catch (e: SQLException) {
            // エラー処理(SQLException)
            throw SQLException("DB処理実施時にエラーが発生しました。")
        } catch (e: Exception) {
            // エラー処理(Exception)
            throw Exception(e.message)
        }
    }

    /**
     * 著者情報をテーブルに登録する
     *
     * @args author 著者情報
     * @return 著者ID
     */
    @Override
    override fun createAuthor(authorDto: AuthorsInfoDto): Int? {
        try {
            // クエリを生成・実行する
            val result = dslContext
                .insertInto(
                    AUTHORS_INFO,
                    AUTHORS_INFO.AUTHOR_NAME,
                    AUTHORS_INFO.BIRTHDAY,
                    AUTHORS_INFO.CREATED_BY,
                    AUTHORS_INFO.CREATED_AT,
                    AUTHORS_INFO.UPDATED_BY,
                    AUTHORS_INFO.UPDATED_AT,
                    AUTHORS_INFO.DELETE_FLG,
                )
                .values(
                    authorDto.authorName, authorDto.birthday, authorDto.createdBy, authorDto.createdAt,
                    authorDto.updatedBy, authorDto.updatedAt, authorDto.deleteFlg
                )
                .returning(AUTHORS_INFO.ID)
                .fetchOne()
            // 実行結果として返ってくる処理件数を返す
            return result?.id
        } catch (e: SQLException) {
            // エラー処理(SQLException)
            throw SQLException("DB処理実施時にエラーが発生しました。")
        } catch (e: Exception) {
            // エラー処理(Exception)　詰め替え時のエラー考慮
            throw Exception(e.message)
        }
    }

    /**
     * 著者情報を更新する
     *
     * @args author 著者情報
     * @return 著者ID
     */
    @Override
    override fun updateAuthor(authorDto: AuthorsInfoDto): Int {
        try {
            // クエリを生成する
            val processedNumber = dslContext
                .update(AUTHORS_INFO)
                .set(AUTHORS_INFO.AUTHOR_NAME, authorDto.authorName)
                .set(AUTHORS_INFO.BIRTHDAY, authorDto.birthday)
                .set(AUTHORS_INFO.UPDATED_BY, authorDto.updatedBy)
                .set(AUTHORS_INFO.UPDATED_AT, authorDto.updatedAt)
                .set(AUTHORS_INFO.DELETE_FLG, authorDto.deleteFlg)
                .where(AUTHORS_INFO.ID.eq(authorDto.id))
                .execute()
            // 実行結果として返ってくる処理件数を返す
            return processedNumber
        } catch (e: SQLException) {
            // エラー処理(SQLException)
            throw SQLException("DB処理実施時にエラーが発生しました。")
        } catch (e: Exception) {
            // エラー処理(Exception)　詰め替え時のエラー考慮
            throw Exception(e.message)
        }
    }
}
