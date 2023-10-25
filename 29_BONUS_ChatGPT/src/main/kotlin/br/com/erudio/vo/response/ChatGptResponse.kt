package br.com.erudio.vo.response

import br.com.erudio.vo.request.Message

data class ChatGptResponse (
    val choices: ArrayList<Choice>
)