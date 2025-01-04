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
import java.sql.SQLException
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
    fun getBook(bookId: Int): Book? {
        // 書籍情報取得
        val resultBook = booksInfoRepositoryImpl.fetchBook(bookId)
        // 著者情報取得
        val resultAuthor = authorIndexRepositoryImpl.getBookFromBookId(bookId)
        // 取得結果による分岐
        return if (resultBook != null && resultAuthor != null) {
            // 取得結果をBookエンティティに詰め替えて返す
            convertBook(
                booksInfoDto = resultBook,
                authorIndexDtoList = resultAuthor
            )
        } else {
            // 取得結果が0件の場合はnullを返す
            null
        }
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
        val createAuthorIndexResult = authorIndexRepositoryImpl.createAuthorIndex(authorIndexDto)
        if (createAuthorIndexResult == book.authorIdList?.size) {
            // 著者人数分処理された場合は書籍IDを返す
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
    fun updateBook(book: Book): String {
        try {
            // 現在の書籍情報を取得する
            // 書籍情報取得
            val currentBookDto = booksInfoRepositoryImpl.fetchBook(
                book.id ?: throw IllegalStateException("書籍IDの値が不正です。")
            ) ?: throw IllegalStateException("書籍情報が取得できませんでした。")
            // 処理日時を取得
            val processingDatetime = common.getProcessingDateTime()
            // Dtoに詰め替える
            val bookInfoDto = convertBooksInfoDto(
                book = book,
                currentBookDto = currentBookDto,
                processingDatetime = processingDatetime
            )
            // 書籍の更新処理
            val resultProcessedNumber = booksInfoRepositoryImpl.updateBook(bookInfoDto)
            // 著者と書籍の紐づきの更新処理
            val resultAuthorIndexUpdate = updateAuthorIndex(
                book = book,
                processingDatetime = processingDatetime
            )
            if (resultProcessedNumber > 0 || resultAuthorIndexUpdate) {
                // 処理された場合更新対象の書籍IDを返す
                return book.id.toString()
            } else {
                // 処理されなかった場合はエラー
                throw Exception("書籍情報の更新に失敗しました。")
            }
        } catch (e: SQLException) {
            // 登録処理時にエラーが発生した場合はエラーメッセージをそのまま入れて投げる
            throw SQLException(e.message)
        } catch (e: Exception) {
            // その他要因で処理されなかった場合はExceptionを投げる
            throw Exception("書籍情報の更新に失敗しました。")
        }
    }

    /**
     * 著者と書籍の紐づきの更新処理
     *
     * @args book 書籍Entity
     * @args processingDatetime 処理日時
     * @return 処理結果
     */
    private fun updateAuthorIndex(
        book: Book,
        processingDatetime: LocalDateTime
    ): Boolean {
        val authorIdList = book.authorIdList
        val bookId = book.id
        // 早期リターンチェック
        if (authorIdList.isNullOrEmpty()) {
            // リクエストの著者リストが未入力の場合は更新対象ではないため処理不要のため未処理としてfalseを返す
            return false
        }
        // 更新前の著者と書籍の紐づきの情報を取得
        val currentAuthorIndexDtoList = authorIndexRepositoryImpl.getBookFromBookId(
            bookId ?: throw IllegalStateException("書籍IDの値が不正です。")
        ) ?: throw IllegalStateException("著者と書籍の紐づきの情報が取得できませんでした。")
        // 現在と更新後の著者の比較
        // 書籍Entityに格納する著者IDリストを作成する
        val currentAuthorIdList = mutableListOf<Int>()
        for (authorIndexDto in currentAuthorIndexDtoList) {
            currentAuthorIdList.add(authorIndexDto.authorId)
        }
        // 同一の著者IDリストの場合は処理不要のため未処理として返す
        if (currentAuthorIdList.size == authorIdList.size) {
            val lastAuthorId = currentAuthorIdList.last()
            // 著者IDに変更がない場合ここまでで処理終了とする
            for (currentAuthorId in currentAuthorIdList) {
                if (!authorIdList.contains(currentAuthorId)) {
                    // 著者IDに一致しないものがある場合は更新処理を行うためチェック処理を抜ける
                    break
                }
                if (currentAuthorId == lastAuthorId) {
                    // 最終周まで著者IDがすべて一致した場合は更新対象ではないため処理不要。未処理としてfalseを返す
                    return false
                }
            }
        }
        // Dtoに詰め替える
        val authorIndexDto = convertAuthorIndexDto(
            book = book,
            processingDatetime = processingDatetime
        )
        // 著者と書籍の紐づきの更新
        val resultUpdateAuthorIndex = authorIndexRepositoryImpl.updateAuthorIndex(
            authorIndexDtoList = authorIndexDto,
            bookId = book.id
        )
        // 更新処理が必要なため、処理件数が0件の場合はエラーとして処理
        return if (resultUpdateAuthorIndex > 0) {
            true
        } else {
            throw SQLException("著者と書籍の紐づきの更新に失敗しました。")
        }
    }

    /**
     * 著者から書籍を取得する
     *
     * @param authorId 著者ID
     * @return 書籍
     */
    fun getBookFromAuthor(authorId: Int): List<Book>? {
        // 著者IDから書籍と著者を紐づけた情報を取得
        val resultAuthorIndexBook = authorIndexRepositoryImpl.getBookFromAuthorId(authorId)
        // 著者IDに紐づく書籍がない場合は処理終了
        if (resultAuthorIndexBook.isNullOrEmpty()) {
            return null
        }
        // 著者が関連している書籍IDを抽出
        val bookIdList = mutableListOf<Int>()
        for (authorIndexDto in resultAuthorIndexBook) {
            bookIdList.add(authorIndexDto.bookId)
        }
        // 書籍情報取得
        val resultBook = mutableListOf<BooksInfoDto>()
        for (bookId in bookIdList) {
            resultBook.add(
                booksInfoRepositoryImpl.fetchBook(bookId)
                    ?: throw IllegalStateException("書籍IDに対応する書籍が存在しません。")
            )
        }
        // 書籍の著者情報取得
        val authorIndexBookMap = mutableMapOf<Int, List<AuthorIndexDto>>()
        for (bookId in bookIdList) {
            authorIndexBookMap[bookId] = authorIndexRepositoryImpl.getBookFromBookId(bookId)
                ?: throw IllegalStateException("書籍IDに対応する書籍が存在しません。")
        }
        val bookList = mutableListOf<Book>()
        for (booksInfoDto in resultBook) {
            bookList.add(
                convertBook(
                    booksInfoDto = booksInfoDto,
                    authorIndexDtoList = authorIndexBookMap[booksInfoDto.id]
                        ?: throw IllegalStateException("書籍IDに対応する書籍が存在しません。")
                )
            )
        }
        return bookList
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
        authorIndexDtoList: List<AuthorIndexDto>
    ): Book {
        // 書籍Entityに格納する著者IDリストを作成する
        val authorIdList = mutableListOf<Int>()
        for (authorIndexDto in authorIndexDtoList) {
            authorIdList.add(authorIndexDto.authorId)
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
     * 書籍と著者を紐づけたEntityをDtoに変換する
     *
     * @args book 書籍Entity
     * @args processingDatetime 処理日時
     * @return 書籍Dto
     */
    private fun convertAuthorIndexDto(
        book: Book,
        processingDatetime: LocalDateTime
    ): List<AuthorIndexDto> {
        val authorIndexDtoList = mutableListOf<AuthorIndexDto>()
        for (authorId in book.authorIdList!!) {
            authorIndexDtoList.add(
                AuthorIndexDto(
                    bookId = book.id?.let {
                        book.id
                    } ?: throw IllegalStateException("書籍IDの値が不正です"),
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
     * @args book 書籍Entity
     * @args currentBookDto 更新前の書籍情報Dto
     * @args processingDatetime 処理日時
     * @return 書籍Dto
     */
    private fun convertBooksInfoDto(
        book: Book,
        currentBookDto: BooksInfoDto,
        processingDatetime: LocalDateTime
    ): BooksInfoDto {
        return BooksInfoDto(
            id = book.id,
            title = book.title ?: currentBookDto.title,
            price = book.price ?: currentBookDto.price,
            publicationStatus = book.publicationStatus?.let {
                book.publicationStatus.code
            } ?: currentBookDto.publicationStatus,
            createdBy = currentBookDto.createdBy,
            createdAt = currentBookDto.createdAt,
            updatedBy = book.operator?.let {
                book.operator
            } ?: throw IllegalStateException("操作者の値が不正です"),
            updatedAt = processingDatetime,
            deleteFlg = book.deleteFlg ?: currentBookDto.deleteFlg,
        )
    }
}
