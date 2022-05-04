#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.vo.v1

data class AccountCredentialsVO(
    val username: String? = null,
    val password: String? = null,
)
