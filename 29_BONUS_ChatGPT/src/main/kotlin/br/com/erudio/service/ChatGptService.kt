package br.com.erudio.service

import br.com.erudio.config.OpenAIConfig
import br.com.erudio.vo.request.ChatGptRequest
import br.com.erudio.vo.request.Message
import br.com.erudio.vo.response.ChatGptResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties.Template
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.logging.Logger

@Service
class ChatGptService {

    private val logger = Logger.getLogger(ChatGptService::class.java.name)

    @Value("\${openai.model}")
    var model: String? = null

    @Value("\${openai.api.url}")
    var apiUrl: String? = null

    @Autowired
    private lateinit var template: RestTemplate

    fun chat(prompt: String): Any {

        logger.info("Starting Prompt")
        val messages = arrayListOf(Message("user", prompt))
        val request = ChatGptRequest(model!!, messages)

        val response = template.postForObject(apiUrl!!, request, ChatGptResponse::class.java)

        logger.info("Processing Prompt")

        return response!!
    }
}