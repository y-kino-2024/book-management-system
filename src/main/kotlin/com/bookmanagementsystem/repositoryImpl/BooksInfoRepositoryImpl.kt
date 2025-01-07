package com.bookmanagementsystem.repositoryImpl

import bookmanagementsystem.jooq.quo_assignment.tables.AuthorsInfo.AUTHORS_INFO
import bookmanagementsystem.jooq.quo_assignment.tables.BooksInfo.BOOKS_INFO
import com.bookmanagementsystem.dto.BooksInfoDto
import com.bookmanagementsystem.repository.BooksInfoRepository
import com.bookmanagementsystem.repositoryImpl.AuthorsInfoRepositoryImpl.Companion.DELETE_FLG_FALSE
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

/**
 * Books_infoのリポジトリクラス
 */
@Repository
class BooksInfoRepositoryImpl(
    private val dslContext: DSLContext
) : BooksInfoRepository {

    companion object {
        // 論理削除されていないカラムの設定値
        const val DELETE_FLG_FALSE = "0"
    }

    /**
     * 書籍情報をDBから取得する
     *
     * @args BookId 書籍ID
     * @return 書籍情報
     */
    @Override
    @Transactional
    override fun fetchBook(bookId: Int): BooksInfoDto? {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの取得
                val result = context.select()
                    .from(BOOKS_INFO)
                    .where(BOOKS_INFO.ID.eq(bookId))
                    .and(BOOKS_INFO.DELETE_FLG.eq(DELETE_FLG_FALSE))
                    .fetchOne()
                // 返り値の生成
                val bookInfoDto = if (result != null) {
                    // 取得出来た場合はdtoに詰め替える
                    BooksInfoDto(
                        id = result.getValue(BOOKS_INFO.ID),
                        title = result.getValue(BOOKS_INFO.TITLE),
                        price = result.getValue(BOOKS_INFO.PRICE),
                        publicationStatus = result.getValue(BOOKS_INFO.PUBLICATION_STATUS),
                        createdBy = result.getValue(BOOKS_INFO.CREATED_BY),
                        createdAt = result.getValue(BOOKS_INFO.CREATED_AT),
                        updatedBy = result.getValue(BOOKS_INFO.UPDATED_BY),
                        updatedAt = result.getValue(BOOKS_INFO.UPDATED_AT),
                        deleteFlg = result.getValue(BOOKS_INFO.DELETE_FLG),
                    )
                } else {
                    // 取得結果が0件の場合はnullを返す
                    null
                }
                // 取得結果を返す
                return@transactionResult bookInfoDto
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
     * 書籍情報をテーブルに登録する
     *
     * @args Book 書籍情報
     * @return 書籍ID
     */
    @Override
    @Transactional
    override fun createBook(bookDto: BooksInfoDto): Int? {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの登録処理
                val result = context.insertInto(
                    BOOKS_INFO,
                    BOOKS_INFO.TITLE,
                    BOOKS_INFO.PRICE,
                    BOOKS_INFO.PUBLICATION_STATUS,
                    BOOKS_INFO.CREATED_BY,
                    BOOKS_INFO.CREATED_AT,
                    BOOKS_INFO.UPDATED_BY,
                    BOOKS_INFO.UPDATED_AT,
                    BOOKS_INFO.DELETE_FLG,
                )
                    .values(
                        bookDto.title, bookDto.price, bookDto.publicationStatus, bookDto.createdBy, bookDto.createdAt,
                        bookDto.updatedBy, bookDto.updatedAt, bookDto.deleteFlg
                    )
                    .returning(BOOKS_INFO.ID)
                    .fetchOne()
                // 実行結果として返ってくる書籍IDを返す
                return@transactionResult result?.id
            }
        } catch (e: SQLException) {
            // エラー処理(SQLException)
            throw SQLException("DB処理実施時にエラーが発生しました。")
        } catch (e: Exception) {
            // エラー処理(Exception)　詰め替え時のエラー考慮
            throw Exception(e.message)
        }
    }

    /**
     * 書籍情報を更新する
     *
     * @args Book 書籍情報
     * @return 書籍ID
     */
    @Override
    @Transactional
    override fun updateBook(bookDto: BooksInfoDto): Int {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの更新処理
                val processedNumber = context.update(BOOKS_INFO)
                    .set(BOOKS_INFO.TITLE, bookDto.title)
                    .set(BOOKS_INFO.PRICE, bookDto.price)
                    .set(BOOKS_INFO.PUBLICATION_STATUS, bookDto.publicationStatus)
                    .set(BOOKS_INFO.UPDATED_BY, bookDto.updatedBy)
                    .set(BOOKS_INFO.UPDATED_AT, bookDto.updatedAt)
                    .set(BOOKS_INFO.DELETE_FLG, bookDto.deleteFlg)
                    .where(BOOKS_INFO.ID.eq(bookDto.id))
                    .execute()
                // 実行結果として返ってくる処理件数を返す
                return@transactionResult processedNumber
            }
        } catch (e: SQLException) {
            // エラー処理(SQLException)
            throw SQLException("DB処理実施時にエラーが発生しました。")
        } catch (e: Exception) {
            // エラー処理(Exception)　詰め替え時のエラー考慮
            throw Exception(e.message)
        }
    }
}
