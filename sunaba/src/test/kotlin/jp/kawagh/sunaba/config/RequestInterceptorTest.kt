package jp.kawagh.sunaba.config

import jp.kawagh.sunaba.controller.AController
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebMvcTest
class RequestInterceptorTest(
    @Autowired private val requestInterceptor: RequestInterceptor,
    @Autowired private val aController: AController,
) {
    @Test
    @DisplayName("Block:me のheaderが設定されていた場合に403を返す")
    fun `return 403 if headers contain Block me`() {
        val mockMvc = MockMvcBuilders
            .standaloneSetup(aController)
            .addInterceptors(requestInterceptor)
            .build()
        mockMvc
            .perform(
                get("/a")
                    .header("Block", "me")
            )
            .andExpect(status().isForbidden)
    }
}