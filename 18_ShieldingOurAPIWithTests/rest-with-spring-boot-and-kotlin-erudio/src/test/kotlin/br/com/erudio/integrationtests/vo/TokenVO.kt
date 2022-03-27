package br.com.erudio.integrationtests.vo

import jakarta.xml.bind.annotation.XmlRootElement
import java.util.*

@XmlRootElement
data class TokenVO(

    val username: String? = null,
    val authenticated: Boolean? = null,
    val created: Date? = null,
    val expiration: Date? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
)
