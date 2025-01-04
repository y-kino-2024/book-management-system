package com.bookmanagementsystem.repositoryImpl

import bookmanagementsystem.jooq.quo_assignment.tables.AuthorIndex.AUTHOR_INDEX
import bookmanagementsystem.jooq.quo_assignment.tables.BooksInfo.BOOKS_INFO
import com.bookmanagementsystem.dto.AuthorIndexDto
import com.bookmanagementsystem.repository.AuthorIndexRepository
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.sql.SQLException

/**
 * author_indexのリポジトリクラス
 */
@Repository
class AuthorIndexRepositoryImpl(
    private val dslContext: DSLContext
) : AuthorIndexRepository {

    /**
     * 著者に紐づく書籍情報をDBから取得する
     *
     * @args authorId 著者ID
     * @return 書籍情報
     */
    @Override
    override fun getBookFromAuthor(authorId: String): List<AuthorIndexDto>? {
        try {
            // クエリを生成・実行する
            val result = this.dslContext.select()
                .from(AUTHOR_INDEX)
                .where(AUTHOR_INDEX.AUTHOR_ID.eq(authorId.toInt()))
                .fetchOne()

            val authorIndexDtoList = mutableListOf<AuthorIndexDto>()
            result?.map { r ->
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
            return authorIndexDtoList
        } catch (e: SQLException) {
            // エラー処理(SQLException)
            throw SQLException("DB処理実施時にエラーが発生しました。")
        } catch (e: Exception) {
            // エラー処理(Exception)
            throw Exception(e.message)
        }
    }

    /**
     * 著者に紐づく書籍情報をDBから取得する
     *
     * @args authorId 著者ID
     * @return 書籍情報
     */
    @Override
    override fun createBookFromAuthor(authorIndexDto: AuthorIndexDto): Int {
        try {
            // クエリを生成・実行する
            val processedNumber = dslContext
                .insertInto(
                    AUTHOR_INDEX,
                    AUTHOR_INDEX.BOOK_ID,
                    AUTHOR_INDEX.AUTHOR_ID,
                    AUTHOR_INDEX.CREATED_BY,
                    AUTHOR_INDEX.CREATED_AT,
                    AUTHOR_INDEX.UPDATED_BY,
                    AUTHOR_INDEX.UPDATED_AT,
                    AUTHOR_INDEX.DELETE_FLG,
                )
                .values(
                    authorIndexDto.bookId, authorIndexDto.authorId, authorIndexDto.createdBy, authorIndexDto.createdAt,
                    authorIndexDto.updatedBy, authorIndexDto.updatedAt, authorIndexDto.deleteFlg
                )
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

    /**
     * 著者に紐づく書籍情報をDBから取得する
     *
     * @args authorId 著者ID
     * @return 書籍情報
     */
    @Override
    override fun updateBookFromAuthor(authorIndexDto: AuthorIndexDto): Int {
        try {
            // クエリを生成する
            val processedNumber = dslContext
                .update(AUTHOR_INDEX)
                .set(AUTHOR_INDEX.UPDATED_BY, authorIndexDto.updatedBy)
                .set(AUTHOR_INDEX.UPDATED_AT, authorIndexDto.updatedAt)
                .set(AUTHOR_INDEX.DELETE_FLG, authorIndexDto.deleteFlg)
                .where(AUTHOR_INDEX.BOOK_ID.eq(authorIndexDto.bookId))
                .and(AUTHOR_INDEX.AUTHOR_ID.eq(authorIndexDto.authorId))
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
