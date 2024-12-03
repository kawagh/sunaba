package jp.kawagh.sunaba

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition
class SunabaApplication

fun main(args: Array<String>) {
	runApplication<SunabaApplication>(*args)
}
