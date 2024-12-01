package jp.kawagh.sunaba

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SunabaApplication

fun main(args: Array<String>) {
	runApplication<SunabaApplication>(*args)
}
