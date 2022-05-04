#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exceptions

import java.util.*

class ExceptionResponse (
    val timestamp: Date,
    val message: String?,
    val details: String
)