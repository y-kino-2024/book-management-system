package com.bookmanagementsystem.service

import com.bookmanagementsystem.controller.AuthorController.Companion.DELETE_FLG_ZERO
import com.bookmanagementsystem.dto.AuthorIndexDto
import com.bookmanagementsystem.dto.BooksInfoDto
import com.bookmanagementsystem.entity.Book
import com.bookmanagementsystem.enumkt.PublicationStatus
import com.bookmanagementsystem.repositoryImpl.AuthorIndexRepositoryImpl
import com.bookmanagementsystem.repositoryImpl.BooksInfoRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
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
    @Autowired
    private val authorIndexRepositoryImpl: AuthorIndexRepositoryImpl,
    @Autowired
    private val booksInfoRepositoryImpl: BooksInfoRepositoryImpl
) {

    /**
     * 書籍IDから書籍を取得する
     *
     * @args bookId 書籍ID
     * @return 書籍Entity
     */
    @Transactional
    fun getBook(bookId: Int): Book? {
        try {
            // 書籍情報取得
            val resultBook = booksInfoRepositoryImpl.fetchBook(bookId)
            // 著者情報取得
            val resultAuthor = authorIndexRepositoryImpl.getBookFromBookId(bookId)
            // 取得結果による分岐
            return if (resultBook != null && resultAuthor != null) {
                // 取得結果を書籍Entityに詰め替えて返す
                convertBook(
                    booksInfoDto = resultBook,
                    authorIndexDtoList = resultAuthor
                )
            } else {
                // 取得結果が0件の場合はnullを返す
                null
            }
        } catch (se: SQLException) {
            // 取得処理時にエラーが発生した場合はエラーメッセージをそのまま入れて投げる
            throw se
        } catch (e: Exception) {
            // その他要因で処理されなかった場合はExceptionを投げる
            throw Exception("書籍情報の取得に失敗しました。")
        }
    }

    /**
     * 書籍の情報を登録する
     *
     * @args book 書籍Entity
     * @return 書籍ID
     */
    @Transactional
    fun createBook(book: Book): Int {
        try {
            // 処理日時を取得
            val processingDatetime = common.getProcessingDateTime()
            // Dtoに詰め替える
            val bookDto = convertCreateBooksInfoDto(
                book = book,
                processingDatetime = processingDatetime
            )
            // 登録処理
            val bookId = booksInfoRepositoryImpl.createBook(bookDto) ?: throw Exception()
            // 書籍IDと登録した書籍情報を書籍と著者を紐づけたDtoに詰め替える
            val authorIndexDto = convertCreateAuthorIndexDto(
                book = book,
                bookId = bookId,
                processingDatetime = processingDatetime
            )
            // 書籍と著者を紐づけた情報を登録
            val createAuthorIndexResult = authorIndexRepositoryImpl.createAuthorIndex(authorIndexDto)
            if (createAuthorIndexResult == book.authorIdList?.size) {
                // 著者人数分処理された場合は書籍IDを返す
                return bookId
            } else {
                // 処理されなかった場合はエラー
                throw Exception()
            }
        } catch (se: SQLException) {
            // 登録処理時にエラーが発生した場合はエラーメッセージをそのまま入れて投げる
            throw se
        } catch (ise: IllegalStateException) {
            // IllegalStateExceptionが発生した場合はエラーメッセージをそのまま入れて投げる
            throw ise
        } catch (e: Exception) {
            // その他要因で処理されなかった場合はExceptionを投げる
            throw Exception("書籍情報の取得に失敗しました。")
        }
    }

    /**
     * 書籍の情報を更新する
     *
     * @args book 書籍Entity
     * @return 書籍ID
     */
    @Transactional
    fun updateBook(book: Book): Int? {
        try {
            // 更新対象の現在の書籍情報を取得する
            val currentBookDto = booksInfoRepositoryImpl.fetchBook(
                book.id?.let {
                    // チェックしているためここで書籍IDがnullになることはない
                    book.id
                } ?: throw IllegalStateException("書籍IDの値が不正です。")
            )
            // 更新対象が存在しない場合はnullを返す
            if (currentBookDto == null) {
                return null
            }
            // 出版状況が変更可能かチェック
            if (
                book.publicationStatus == PublicationStatus.UNPUBLISHED &&
                PublicationStatus.getPublicationStatus(currentBookDto.publicationStatus) == PublicationStatus.PUBLISHED
            ) {
                // 出版済み→未出版への変更は不可能なためエラーとして返す
                throw IllegalStateException("出版状況は出版済みから未出版に変更出来ません。")
            }
            // 処理日時を取得
            val processingDatetime = common.getProcessingDateTime()
            // Dtoに詰め替える
            val bookInfoDto = convertUpdateBooksInfoDto(
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
                return book.id
            } else {
                // 処理されなかった場合はエラー
                throw Exception()
            }
        } catch (se: SQLException) {
            // 更新処理時にエラーが発生した場合はそのまま投げる
            throw se
        } catch (ise: IllegalStateException) {
            // IllegalStateExceptionが発生した場合はそのまま投げる
            throw ise
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
            bookId ?: throw IllegalStateException("bookIdの値が不正です。")
        )
        // 更新対象が取得できなかった場合はエラーとして処理する
        if (currentAuthorIndexDtoList == null) {
            throw IllegalStateException("著者と書籍の紐づきの情報が取得できませんでした。")
        }
        // 現在と更新後の著者の比較
        // 書籍Entityに格納する著者IDリストを作成する
        val currentAuthorIdList = mutableListOf<Int>()
        for (authorIndexDto in currentAuthorIndexDtoList) {
            currentAuthorIdList.add(authorIndexDto.authorId)
        }
        // 現在と更新後の著者IDリストが同一の場合は処理不要のため未処理として返す
        if (currentAuthorIdList.size == authorIdList.size) {
            val lastAuthorId = currentAuthorIdList.last()
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
        val authorIndexDto = convertUpdateAuthorIndexDto(
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
            throw Exception()
        }
    }

    /**
     * 著者から書籍を取得する
     *
     * @args authorId 著者ID
     * @return 書籍Entityのリスト
     */
    fun getBookFromAuthor(authorId: Int): List<Book>? {
        try {
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
        } catch (e: SQLException) {
            // 登録処理時にエラーが発生した場合はエラーメッセージをそのまま入れて投げる
            throw SQLException(e.message)
        } catch (e: Exception) {
            // その他要因で処理されなかった場合はExceptionを投げる
            throw Exception("書籍情報の更新に失敗しました。")
        }
    }

    /**
     * 書籍のDtoと書籍と著者を紐づけたDtoを書籍のEntityに変換する
     *
     * @args booksInfoDto 書籍Dto
     * @args authorIndexDto 書籍と著者を紐づけたDtoリスト
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
     * @args bookId 書籍ID
     * @args processingDatetime 処理日時
     * @return 書籍と著者を紐づけたDtoのリスト
     */
    private fun convertCreateAuthorIndexDto(
        book: Book,
        bookId: Int,
        processingDatetime: LocalDateTime
    ): List<AuthorIndexDto> {
        val authorIndexDtoList = mutableListOf<AuthorIndexDto>()
        val authorIdList = book.authorIdList.let {
            // authorIdListはnullチェック済みのためnullになることはない
            book.authorIdList
        } ?: throw IllegalStateException("authorIdListの値が不正です。")
        for (authorId in authorIdList) {
            authorIndexDtoList.add(
                AuthorIndexDto(
                    bookId = bookId,
                    authorId = authorId,
                    createdBy = book.operator?.let {
                        book.operator
                    } ?: throw IllegalStateException("createdByの値が不正です。"),
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
     * @return 書籍と著者を紐づけたDtoのリスト
     */
    private fun convertUpdateAuthorIndexDto(
        book: Book,
        processingDatetime: LocalDateTime
    ): List<AuthorIndexDto> {
        val authorIndexDtoList = mutableListOf<AuthorIndexDto>()
        val authorIdList = book.authorIdList.let {
            // authorIdListはnullチェック済みのためnullになることはない
            book.authorIdList
        } ?: throw IllegalStateException("authorIdListの値が不正です。")
        for (authorId in authorIdList) {
            authorIndexDtoList.add(
                AuthorIndexDto(
                    bookId = book.id?.let {
                        book.id
                    } ?: throw IllegalStateException("bookIdの値が不正です"),
                    authorId = authorId,
                    createdBy = book.operator?.let {
                        book.operator
                    } ?: throw IllegalStateException("createdByの値が不正です"),
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
    private fun convertCreateBooksInfoDto(
        book: Book,
        processingDatetime: LocalDateTime
    ): BooksInfoDto {
        return BooksInfoDto(
            id = null,
            title = book.title?.let {
                book.title
            } ?: throw IllegalStateException("titleの値が不正です"),
            price = book.price?.let {
                book.price.toDouble()
            } ?: throw IllegalStateException("priceの値が不正です"),
            publicationStatus = book.publicationStatus?.let {
                book.publicationStatus.code
            } ?: throw IllegalStateException("publicationStatusの値が不正です"),
            createdBy = book.operator?.let {
                book.operator
            } ?: throw IllegalStateException("createdByの値が不正です"),
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
     * @args currentBookDto 更新前の書籍Dto
     * @args processingDatetime 処理日時
     * @return 書籍Dto
     */
    private fun convertUpdateBooksInfoDto(
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
            } ?: throw IllegalStateException("updatedByの値が不正です"),
            updatedAt = processingDatetime,
            deleteFlg = book.deleteFlg ?: currentBookDto.deleteFlg,
        )
    }
}
