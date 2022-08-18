package com.knitting.kneedle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KneedleApplication

fun main(args: Array<String>) {
    runApplication<KneedleApplication>(*args)
}
