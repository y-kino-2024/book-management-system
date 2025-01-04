package com.bookmanagementsystem.service

import com.bookmanagementsystem.controller.AuthorController.Companion.DELETE_FLG_ZERO
import com.bookmanagementsystem.dto.AuthorIndexDto
import com.bookmanagementsystem.dto.BooksInfoDto
import com.bookmanagementsystem.entity.Book
import com.bookmanagementsystem.enumkt.PublicationStatus
import com.bookmanagementsystem.repositoryImpl.AuthorIndexRepositoryImpl
import com.bookmanagementsystem.repositoryImpl.BooksInfoRepositoryImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * 書籍のService
 */
@Service
class BookService(
    private val common: CommonService,
    private val authorIndexRepositoryImpl: AuthorIndexRepositoryImpl,
    private val booksInfoRepositoryImpl: BooksInfoRepositoryImpl
) {

    /**
     * 書籍IDから書籍を取得する
     *
     * @param bookId 書籍ID
     * @return 書籍
     */
    @Transactional
    fun getBook(bookId: String): Book {
        // TODO 処理未実装。エラーださないための仮置き
        //val enum = PublicationStatus.NONE.code
        return Book(
            id = 99999999,
            authorIdList = listOf(2, 4, 6, 8, 10),
            title = "test",
            price = 8888.0,
            publicationStatus = PublicationStatus.NONE,
            operator = "test",
            deleteFlg = "0"
        )
    }

    /**
     * 書籍の情報を登録する
     *
     * @param book 書籍
     * @return 書籍ID
     */
    @Transactional
    fun createBook(book: Book): String {
        // 処理日時を取得
        val processingDatetime = common.getProcessingDateTime()
        // Dtoに詰め替える
        val bookDto = convertBooksInfoDto(
            book = book,
            processingDatetime = processingDatetime
        )
        // 登録処理
        val bookId = booksInfoRepositoryImpl.createBook(bookDto) ?: throw Exception("著者情報の登録に失敗しました。")
        // 書籍IDと登録した書籍情報を書籍と著者を紐づけたDtoに詰め替える
        val authorIndexDto = convertAuthorIndexDto(
            book = book,
            bookId = bookId,
            processingDatetime = processingDatetime
        )
        // 書籍と著者を紐づけた情報を登録
        val createAuthorIndexResult = authorIndexRepositoryImpl.createBookFromAuthor(authorIndexDto)
        println("処理件数$createAuthorIndexResult")
        if (createAuthorIndexResult == book.authorIdList?.size) {
            // 著者人数分処理された場合
            return bookId.toString()
        } else {
            // 処理されなかった場合はエラー
            throw Exception("著者情報の登録に失敗しました。")
        }
    }

    /**
     * 書籍の情報を更新する
     *
     * @param book 書籍
     * @return
     */
    @Transactional
    fun updateBook(book: Book) {
        // TODO 処理未実装。
        // TODO 引数の書籍Entity内のIDで取得→取得の結果で分岐→処理結果をどう返す？
    }

    /**
     * 著者から書籍を取得する
     *
     * @param authorId 著者ID
     * @return 書籍
     */
    fun getBookFromAuthor(authorId: String): Book {
        // TODO 処理未実装。エラーださないための仮置き
        return Book(
            id = 99999999,
            authorIdList = listOf(1, 3, 5),
            title = "test",
            price = 8888.0,
            publicationStatus = PublicationStatus.getPublicationStatus("0"),
            operator = "test",
            deleteFlg = "0"
        )
    }

    /**
     * 書籍のDtoと書籍と著者を紐づけたDtoを書籍のEntityに変換する
     *
     * @args booksInfoDto 書籍Dto
     * @args authorIndexDto 書籍と著者を紐づけたDto
     * @return 書籍Entity
     */
    private fun convertBook(
        booksInfoDto: BooksInfoDto,
        authorIndexDto: List<AuthorIndexDto>
    ): Book {
        // 書籍Entityに格納する著者IDリストを作成する
        val authorIdList = mutableListOf<Int>()
        for (authorIndex in authorIndexDto) {
            authorIdList.add(authorIndex.authorId)
        }
        return Book(
            id = booksInfoDto.id,
            authorIdList = authorIdList,
            title = booksInfoDto.title,
            price = booksInfoDto.price,
            publicationStatus = PublicationStatus.getPublicationStatus(booksInfoDto.publicationStatus),
            operator = booksInfoDto.updatedBy,
            deleteFlg = booksInfoDto.deleteFlg
        )
    }

    /**
     * 書籍と著者を紐づけたEntityをDtoに変換する
     *
     * @args book 書籍Entity
     * @args processingDatetime 処理日時
     * @return 書籍Dto
     */
    private fun convertAuthorIndexDto(
        book: Book,
        bookId: Int,
        processingDatetime: LocalDateTime
    ): List<AuthorIndexDto> {
        val authorIndexDtoList = mutableListOf<AuthorIndexDto>()
        for (authorId in book.authorIdList!!) {
            authorIndexDtoList.add(
                AuthorIndexDto(
                    bookId = bookId,
                    authorId = authorId,
                    createdBy = book.operator?.let {
                        book.operator
                    } ?: throw IllegalStateException("操作者の値が不正です"),
                    createdAt = processingDatetime,
                    updatedBy = book.operator,
                    updatedAt = processingDatetime,
                    deleteFlg = DELETE_FLG_ZERO
                )
            )
        }
        return authorIndexDtoList
    }

    /**
     * 書籍のEntityをDtoに変換する
     *
     * @args book 書籍Entity
     * @args processingDatetime 処理日時
     * @return 書籍Dto
     */
    private fun convertBooksInfoDto(
        book: Book,
        processingDatetime: LocalDateTime
    ): BooksInfoDto {
        return BooksInfoDto(
            id = null,
            title = book.title?.let {
                book.title
            } ?: throw IllegalStateException("タイトルの値が不正です"),
            price = book.price?.let {
                book.price.toDouble()
            } ?: throw IllegalStateException("価格の値が不正です"),
            publicationStatus = book.publicationStatus?.let {
                book.publicationStatus.code
            } ?: throw IllegalStateException("出版状況の値が不正です"),
            createdBy = book.operator?.let {
                book.operator
            } ?: throw IllegalStateException("操作者の値が不正です"),
            createdAt = processingDatetime,
            updatedBy = book.operator,
            updatedAt = processingDatetime,
            deleteFlg = DELETE_FLG_ZERO,
        )
    }

    /**
     * 書籍のEntityをDtoに変換する
     *
     * @args author 書籍Entity
     * @args processingDatetime 処理日時
     * @return 書籍Dto
     */
    private fun convertBooksInfoDto(
        book: Book,
        currentBook: BooksInfoDto,
        processingDatetime: LocalDateTime
    ): BooksInfoDto {
        return BooksInfoDto(
            id = book.id,
            title = book.title ?: currentBook.title,
            price = book.price ?: currentBook.price,
            publicationStatus = book.publicationStatus?.let {
                book.publicationStatus.code
            } ?: currentBook.publicationStatus,
            createdBy = currentBook.createdBy,
            createdAt = currentBook.createdAt,
            updatedBy = book.operator?.let {
                book.operator
            } ?: throw IllegalStateException("操作者の値が不正です"),
            updatedAt = processingDatetime,
            deleteFlg = book.deleteFlg ?: currentBook.deleteFlg,
        )
    }
}
