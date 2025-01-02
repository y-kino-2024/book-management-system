package com.bookmanagementsystem.service

import com.bookmanagementsystem.entity.Book
import com.bookmanagementsystem.enum.PublicationStatus
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * 書籍のService
 */
@SpringBootApplication
class BookService {

    /**
     * 書籍IDから書籍を取得する
     * @param bookId 書籍ID
     * @return 書籍
     */
    fun getBook(bookId: String): Book {
        // TODO 処理未実装。エラーださないための仮置き
        //val enum = PublicationStatus.NONE.code
        return Book(
            id = "test",
            authorId = listOf("test"),
            title = "test",
            price = "test",
            publicationStatus = PublicationStatus.NONE
        )
    }

    /**
     * 書籍の情報を登録する
     * @param book 書籍
     * @return 書籍ID
     */
    fun createBook(book: Book): String {
        // TODO 処理未実装。
        // TODO 著者IDに対応する著者がいない場合はエラーとして返す

        // TODO 引数のbookはID以外すべて入力された状態として、登録処理を行う。

        // TODO 登録処理1：書籍情報の登録

        // TODO 登録処理2：書籍と著者を紐づけたテーブルへの登録

        //　TODO 登録した書籍情報の書籍IDを返す

        return "test"
    }

    /**
     * 書籍の情報を更新する
     * @param book 書籍
     * @return
     */
    fun updateBook(book: Book) {
        // TODO 処理未実装。
        // TODO 引数の書籍Entity内のIDで取得→取得の結果で分岐→処理結果をどう返す？
    }

    /**
     * 筆者から書籍を取得する
     * @param authorId 筆者ID
     * @return 書籍
     */
    fun getBookFromAuthor(authorId: String): Book {
        // TODO 処理未実装。エラーださないための仮置き
        return Book(
            id = "test",
            authorId = listOf("test"),
            title = "test",
            price = "test",
            publicationStatus = PublicationStatus.valueOf("9")
        )
    }
}
