package jp.kawagh.sunaba.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
class AControllerTests {
    private val aController: AController = AController()
    private val mockMvc = MockMvcBuilders.standaloneSetup(aController).build()

    @Test
    @DisplayName("GET /a")
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
    @DisplayName("GET /a/2")
    fun a2() {
        val expectedResponse: AController.AResponse = AController.AResponse("aWithNumber", 2)
        val expected = ObjectMapper().writeValueAsString(expectedResponse)

        val resultResponse = mockMvc.perform(get("/a/{number}", 2))
                .andExpect(status().isOk)
                .andReturn().response
        val result = resultResponse.contentAsString

        Assertions.assertEquals(expected, result)
    }
}