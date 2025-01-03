package com.bookmanagementsystem.repositoryImpl

import com.bookmanagementsystem.dto.BooksInfoDto
import com.bookmanagementsystem.repository.AuthorIndexRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * author_indexのリポジトリクラス
 */
@Repository
class AuthorIndexRepositoryImpl : AuthorIndexRepository {

    /**
     * 著者に紐づく書籍情報をDBから取得する
     *
     * @args authorId 著者ID
     * @return 書籍情報
     */
    @Override
    override fun getBookFromAuthor(authorId: String): BooksInfoDto? {
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
}
