package br.com.erudio.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate
import java.util.logging.Logger

@Configuration
class OpenAIConfig {

    private val logger = Logger.getLogger(OpenAIConfig::class.java.name)

    @Value("\${openai.api.key}")
    private lateinit var openaiApiKey: String

    @Bean
    fun template(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.interceptors.add(
            ClientHttpRequestInterceptor {
                request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution ->
                request.headers.add("Authorization", "Bearer $openaiApiKey")
                execution.execute(request, body)
            })
        return restTemplate
    }
}