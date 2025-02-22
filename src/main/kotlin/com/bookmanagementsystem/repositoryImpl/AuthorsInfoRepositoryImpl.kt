package com.bookmanagementsystem.repositoryImpl

import bookmanagementsystem.jooq.quo_assignment.tables.AuthorsInfo.AUTHORS_INFO
import com.bookmanagementsystem.dto.AuthorsInfoDto
import com.bookmanagementsystem.repository.AuthorsInfoRepository
import org.jooq.DSLContext
import org.jooq.impl.DSL
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

    companion object {
        // 論理削除されていないカラムの設定値
        const val DELETE_FLG_FALSE = "0"
    }

    /**
     * 著者情報をDBから取得する
     *
     * @args authorId 著者ID
     * @return 著者情報
     */
    @Override
    @Transactional
    override fun fetchAuthor(authorId: Int): AuthorsInfoDto? {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの取得
                val result = context.select()
                    .from(AUTHORS_INFO)
                    .where(AUTHORS_INFO.ID.eq(authorId))
                    .and(AUTHORS_INFO.DELETE_FLG.eq(DELETE_FLG_FALSE))
                    .fetchOne()
                // 返り値の生成
                val authorDto = if (result != null) {
                    // 取得出来た場合はdtoに詰め替える
                    AuthorsInfoDto(
                        id = result.getValue(AUTHORS_INFO.ID),
                        authorName = result.getValue(AUTHORS_INFO.AUTHOR_NAME),
                        birthday = result.getValue(AUTHORS_INFO.BIRTHDAY),
                        createdBy = result.getValue(AUTHORS_INFO.CREATED_BY),
                        createdAt = result.getValue(AUTHORS_INFO.CREATED_AT),
                        updatedBy = result.getValue(AUTHORS_INFO.UPDATED_BY),
                        updatedAt = result.getValue(AUTHORS_INFO.UPDATED_AT),
                        deleteFlg = result.getValue(AUTHORS_INFO.DELETE_FLG),
                    )
                } else {
                    // 取得結果が0件の場合はnullを返す
                    null
                }
                // 取得結果を返す
                return@transactionResult authorDto
            }
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
    @Transactional
    override fun createAuthor(authorDto: AuthorsInfoDto): Int? {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの登録処理
                val result = context.insertInto(
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
                // 実行結果として返ってくる著者IDを返す
                return@transactionResult result?.id
            }
        } catch (e: SQLException) {
            // エラー処理(SQLException)
            throw SQLException("DB処理実施時にエラーが発生しました。")
        } catch (e: Exception) {
            // エラー処理(Exception) 詰め替え時のエラー考慮
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
    @Transactional
    override fun updateAuthor(authorDto: AuthorsInfoDto): Int {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの更新処理
                val processedNumber = context.update(AUTHORS_INFO)
                    .set(AUTHORS_INFO.AUTHOR_NAME, authorDto.authorName)
                    .set(AUTHORS_INFO.BIRTHDAY, authorDto.birthday)
                    .set(AUTHORS_INFO.UPDATED_BY, authorDto.updatedBy)
                    .set(AUTHORS_INFO.UPDATED_AT, authorDto.updatedAt)
                    .set(AUTHORS_INFO.DELETE_FLG, authorDto.deleteFlg)
                    .where(AUTHORS_INFO.ID.eq(authorDto.id))
                    .execute()
                // 実行結果として返ってくる処理件数を返す
                return@transactionResult processedNumber
            }
        } catch (e: SQLException) {
            // エラー処理(SQLException)
            throw SQLException("DB処理実施時にエラーが発生しました。")
        } catch (e: Exception) {
            // エラー処理(Exception) 詰め替え時のエラー考慮
            throw Exception(e.message)
        }
    }
}
