package com.bookmanagementsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * 起動クラス
 */
@SpringBootApplication
class BootApplication

fun main(args: Array<String>) {
    runApplication<BootApplication>(*args)
}
