package br.com.erudio.integrationtests.vo

import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class AccountCredentialsVO(
    val username: String? = null,
    val password: String? = null,
)
