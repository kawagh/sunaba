package jp.kawagh.sunaba.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebConfig(@Autowired private val requestInterceptor: RequestInterceptor) : WebMvcConfigurationSupport() {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor)
            .addPathPatterns("/**")
    }
}