package com.bookmanagementsystem.repositoryImpl

import bookmanagementsystem.jooq.quo_assignment.tables.AuthorIndex.AUTHOR_INDEX
import bookmanagementsystem.jooq.quo_assignment.tables.BooksInfo.BOOKS_INFO
import com.bookmanagementsystem.dto.AuthorIndexDto
import com.bookmanagementsystem.repository.AuthorIndexRepository
import com.bookmanagementsystem.repositoryImpl.BooksInfoRepositoryImpl.Companion.DELETE_FLG_FALSE
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

/**
 * author_indexのリポジトリクラス
 */
@Repository
class AuthorIndexRepositoryImpl(
    private val dslContext: DSLContext
) : AuthorIndexRepository {

    companion object {
        // 論理削除されていないカラムの設定値
        const val DELETE_FLG_FALSE = "0"
    }

    /**
     * 書籍IDを用いて著者に紐づく書籍情報をDBから取得する
     *
     * @args bookId 書籍ID
     * @return 著者に紐づく書籍情報
     */
    @Override
    @Transactional
    override fun getBookFromBookId(bookId: Int): List<AuthorIndexDto>? {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの取得
                val result = context.select()
                    .from(AUTHOR_INDEX)
                    .where(AUTHOR_INDEX.BOOK_ID.eq(bookId))
                    .and(AUTHOR_INDEX.DELETE_FLG.eq(DELETE_FLG_FALSE))
                    .fetch()
                // 取得結果をAuthorIndexDtoに詰め替える
                val authorIndexDtoList = mutableListOf<AuthorIndexDto>()
                result.map { r ->
                    authorIndexDtoList.add(
                        AuthorIndexDto(
                            bookId = r.getValue(AUTHOR_INDEX.BOOK_ID),
                            authorId = r.getValue(AUTHOR_INDEX.AUTHOR_ID),
                            createdBy = r.getValue(AUTHOR_INDEX.CREATED_BY),
                            createdAt = r.getValue(AUTHOR_INDEX.CREATED_AT),
                            updatedBy = r.getValue(AUTHOR_INDEX.UPDATED_BY),
                            updatedAt = r.getValue(AUTHOR_INDEX.UPDATED_AT),
                            deleteFlg = r.getValue(AUTHOR_INDEX.DELETE_FLG),
                        )
                    )
                }
                // 取得結果を返す
                return@transactionResult authorIndexDtoList
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
     * 著者IDを用いて著者に紐づく書籍情報をDBから取得する
     *
     * @args authorId 著者ID
     * @return 著者に紐づく書籍情報
     */
    @Override
    @Transactional
    override fun getBookFromAuthorId(authorId: Int): List<AuthorIndexDto>? {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの取得
                val result = context.select()
                    .from(AUTHOR_INDEX)
                    .where(AUTHOR_INDEX.AUTHOR_ID.eq(authorId))
                    .and(AUTHOR_INDEX.DELETE_FLG.eq(DELETE_FLG_FALSE))
                    .fetch()
                // 取得結果をAuthorIndexDtoに詰め替える
                val authorIndexDtoList = result.map { r ->
                    AuthorIndexDto(
                        bookId = r.getValue(AUTHOR_INDEX.BOOK_ID),
                        authorId = r.getValue(AUTHOR_INDEX.AUTHOR_ID),
                        createdBy = r.getValue(AUTHOR_INDEX.CREATED_BY),
                        createdAt = r.getValue(AUTHOR_INDEX.CREATED_AT),
                        updatedBy = r.getValue(AUTHOR_INDEX.UPDATED_BY),
                        updatedAt = r.getValue(AUTHOR_INDEX.UPDATED_AT),
                        deleteFlg = r.getValue(AUTHOR_INDEX.DELETE_FLG),
                    )
                }
                // 取得結果を返す
                return@transactionResult authorIndexDtoList
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
     * 著者に紐づく書籍情報をDBに登録する
     *
     * @args authorIndexDtoList 著者に紐づく書籍情報
     * @return 処理件数
     */
    @Override
    @Transactional
    override fun createAuthorIndex(authorIndexDtoList: List<AuthorIndexDto>): Int {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // データの登録処理
                context.batch(
                    authorIndexDtoList.map { authorIndexDto ->
                        context.insertInto(AUTHOR_INDEX)
                            .set(AUTHOR_INDEX.BOOK_ID, authorIndexDto.bookId)
                            .set(AUTHOR_INDEX.AUTHOR_ID, authorIndexDto.authorId)
                            .set(AUTHOR_INDEX.CREATED_BY, authorIndexDto.createdBy)
                            .set(AUTHOR_INDEX.CREATED_AT, authorIndexDto.createdAt)
                            .set(AUTHOR_INDEX.UPDATED_BY, authorIndexDto.updatedBy)
                            .set(AUTHOR_INDEX.UPDATED_AT, authorIndexDto.createdAt)
                            .set(AUTHOR_INDEX.DELETE_FLG, authorIndexDto.deleteFlg)
                    }
                    // 登録処理件数を算出
                ).execute().sum()
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
     * 著者に紐づく書籍情報をDB更新する
     *
     * @args authorIndexDto 著者に紐づく書籍情報
     * @return 処理件数
     */
    @Override
    @Transactional
    override fun updateAuthorIndex(authorIndexDtoList: List<AuthorIndexDto>, bookId: Int): Int {
        try {
            return dslContext.transactionResult { configuration ->
                val context = DSL.using(configuration)
                // DELETE-INSERTを行う
                // 更新前の書籍と著者の紐づけを削除する
                val deleteProcessedNumber = context
                    .delete(AUTHOR_INDEX)
                    .where(AUTHOR_INDEX.BOOK_ID.eq(bookId))
                    .execute()
                // 処理結果が0件の場合は処理が出来ていないためエラー扱いとする
                if (deleteProcessedNumber == 0) {
                    throw SQLException()
                }
                // 書籍と著者の紐づけを作成する
                // データの登録処理
                val createProcessedNumber = context.batch(
                    authorIndexDtoList.map { authorIndexDto ->
                        context.insertInto(AUTHOR_INDEX)
                            .set(AUTHOR_INDEX.BOOK_ID, authorIndexDto.bookId)
                            .set(AUTHOR_INDEX.AUTHOR_ID, authorIndexDto.authorId)
                            .set(AUTHOR_INDEX.CREATED_BY, authorIndexDto.createdBy)
                            .set(AUTHOR_INDEX.CREATED_AT, authorIndexDto.createdAt)
                            .set(AUTHOR_INDEX.UPDATED_BY, authorIndexDto.updatedBy)
                            .set(AUTHOR_INDEX.UPDATED_AT, authorIndexDto.createdAt)
                            .set(AUTHOR_INDEX.DELETE_FLG, authorIndexDto.deleteFlg)
                    }
                    // 登録処理件数を算出
                ).execute().sum()
                // 実行結果として返ってくる処理件数を返す
                return@transactionResult createProcessedNumber
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
