package com.bookmanagementsystem.repositoryImpl

import com.bookmanagementsystem.dto.BooksInfoDto
import com.bookmanagementsystem.repository.BooksInfoRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * Books_infoのリポジトリクラス
 */
@Repository
class BooksInfoRepositoryImpl : BooksInfoRepository {

    /**
     * 書籍情報をDBから取得する
     * @args BookId 書籍ID
     * @return 書籍情報
     */
    @Override
    override fun getBook(bookId: String): BooksInfoDto? {
        // TODO 処理内容
        // クエリを生成する
        // 取得した内容をDtoに詰め替える
        // Dtoを返す

        // TODO 動確用返り値
        return BooksInfoDto(
            id = "test",
            title = "test",
            price = "test",
            publicationStatus = "0",
            createdBy = "test",
            createdAt = LocalDateTime.now(),
            updatedBy = "test",
            updatedAt = LocalDateTime.now(),
            deleteFlg = "0",
        )
    }

    /**
     * 書籍情報をテーブルに登録する
     * @args Book 書籍情報
     * @return 書籍ID
     */
    @Override
    override fun createBook(book: BooksInfoDto): String {
        // TODO 処理内容
        // クエリを生成する
        // クエリを実行する
        // 書籍IDを返す

        // TODO 動確用返り値
        return "createTest"
    }

    /**
     * 書籍情報を更新する
     * @args Book 書籍情報
     * @return 書籍ID
     */
    @Override
    override fun updateBook(book: BooksInfoDto): String {
        // TODO 処理内容
        // クエリを生成する
        // クエリを実行する
        // 書籍IDを返す

        // TODO 動確用返り値
        return "updateTest"
    }

}
