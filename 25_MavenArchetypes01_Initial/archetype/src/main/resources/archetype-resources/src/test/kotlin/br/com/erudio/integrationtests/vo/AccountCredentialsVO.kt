#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtests.vo

import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class AccountCredentialsVO(
    var username: String? = null,
    var password: String? = null,
)
