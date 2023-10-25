package br.com.erudio.vo.response

import br.com.erudio.vo.request.Message

data class Choice (
    var index: Int = 0,
    var message: Message
)