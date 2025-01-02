package com.bookmanagementsystem.service

import com.bookmanagementsystem.entity.Author
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.sql.Date
import java.time.LocalDate

/**
 * 著者のService
 */
@SpringBootApplication
class AuthorService {

    /**
     * 著者IDから著者を取得する
     * @param authorId 著者ID
     * @return 著者
     */
    fun getAuthor(authorId: String): Author {
        // TODO 処理未実装。エラーださないための仮置き
        // FIXME SQL作成
        //val sql: String = create.select().from(ACTOR).getSQL()
        return Author(
            id = "test",
            authorName = "test",
            birthday = Date.valueOf(LocalDate.now()),
        )
    }

    /**
     * 著者の情報を登録する
     * @param author 著者
     * @return 著者ID
     */
    fun createAuthor(author: Author): String {
        // TODO 処理未実装。
        // TODO 引数のauthorはID以外すべて入力された状態として、登録処理を行う。
        // チェック処理
        // 登録処理

        return "test"
    }

    /**
     * 著者の情報を更新する
     * @param author 著者
     * @return
     */
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
     * @param authorId 著者ID
     * @return
     */
    /*
    fun deleteAuthor(authorId: String) {
        runApplication<AuthorModel>(*args)
    }
     */
}
