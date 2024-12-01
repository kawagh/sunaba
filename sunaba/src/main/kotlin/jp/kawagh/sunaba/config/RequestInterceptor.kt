package jp.kawagh.sunaba.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor


@Component
class RequestInterceptor : HandlerInterceptor {
    // 以下のようなinterceptorは別々にする
    // リクエストを通すか否かのinterceptor
    // リクエストのログを出力するinterceptor
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.getHeader("Block") == "me") {
            response.status = HttpServletResponse.SC_FORBIDDEN
            return false
        }
        return true
    }
}