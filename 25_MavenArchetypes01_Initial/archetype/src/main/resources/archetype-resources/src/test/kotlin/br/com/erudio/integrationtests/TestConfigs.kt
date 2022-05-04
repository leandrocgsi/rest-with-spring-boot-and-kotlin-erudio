#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtests

object TestConfigs {
    const val SERVER_PORT = 8888

    const val HEADER_PARAM_ORIGIN = "Origin"
    const val HEADER_PARAM_AUTHORIZATION = "Authorization"

    const val CONTENT_TYPE_JSON = "application/json"
    const val CONTENT_TYPE_XML = "application/xml"

    const val CONTENT_TYPE_YML = "application/x-yaml"

    const val ORIGIN_ERUDIO = "https://erudio.com.br"
    const val ORIGIN_SEMERU = "https://semeru.com.br"
    const val ORIGIN_LOCALHOST = "http://localhost:8080"
}