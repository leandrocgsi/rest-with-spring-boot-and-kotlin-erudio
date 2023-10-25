package br.com.erudio.vo.request

data class ChatGptRequest (
    val model: String,
    val messages: ArrayList<Message>
)