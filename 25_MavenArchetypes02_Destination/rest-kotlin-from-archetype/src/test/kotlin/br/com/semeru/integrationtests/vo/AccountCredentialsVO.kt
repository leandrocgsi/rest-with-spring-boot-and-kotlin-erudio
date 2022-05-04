package br.com.semeru.integrationtests.vo

import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class AccountCredentialsVO(
    var username: String? = null,
    var password: String? = null,
)
