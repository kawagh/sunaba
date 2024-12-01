package jp.kawagh.sunaba.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/a")
class AController {
    @GetMapping
    fun a(): AResponse {
        return AResponse("a", 1)
    }

    @GetMapping("/{number}")
    fun aWithNumber(@PathVariable number: Int): AResponse {
        return AResponse("aWithNumber", number)
    }

    data class AResponse(val name: String, val number: Int)
}