package jp.kawagh.sunaba.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AController::class)
class AControllerTests(@Autowired private val mockMvc: MockMvc) {

    @Nested
    @DisplayName("学習用テスト")
    inner class LearningTest {
        @Test
        @DisplayName("GET /a のレスポンスの検証(ObjectMapperとAResponseクラスの挙動による)")
        fun a() {
            val expectedResponse: AController.AResponse = AController.AResponse("a", 1)
            val expected = ObjectMapper().writeValueAsString(expectedResponse)
            val resultResponse = mockMvc.perform(get("/a"))
                    .andExpect(status().isOk)
                    .andReturn().response
            val result = resultResponse.contentAsString

            Assertions.assertEquals(expected, result)
        }

        @Test
        @DisplayName("GET /a/2 (ObjectMapperの挙動によらずにjsonの中身を検証できる)")
        fun a2() {
            mockMvc.perform(get("/a/{number}", 2))
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.name").value("aWithNumber"))
                    .andExpect(jsonPath("$.number").value(2))
        }

        @Test
        @DisplayName("Intの最大値を超える値を受け取ったときstatusは400")
        fun a3() {
            mockMvc.perform(get("/a/{number}", Int.MAX_VALUE.toLong() + 1))
                    .andExpect(status().`is`(400))
        }
    }
}