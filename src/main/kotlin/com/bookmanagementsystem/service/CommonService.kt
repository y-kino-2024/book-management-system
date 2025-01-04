package com.bookmanagementsystem.service

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 共通処理のサービス
 */
@Service
class CommonService {

    /**
     * 処理日時を取得する
     *
     * @return 処理日時
     */
    fun getProcessingDateTime(): LocalDateTime {
        val now = LocalDateTime.now()
        // ミリ秒まで表示させるように整形
        val processingDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss.SSS")
        val processingDateTime = LocalDateTime.parse(now.format(processingDateFormat), processingDateFormat)
        return processingDateTime
    }
}