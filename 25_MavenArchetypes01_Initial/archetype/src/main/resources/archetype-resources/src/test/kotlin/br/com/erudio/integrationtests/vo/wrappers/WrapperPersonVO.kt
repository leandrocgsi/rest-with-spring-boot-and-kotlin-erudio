#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtests.vo.wrappers

import com.fasterxml.jackson.annotation.JsonProperty

class WrapperPersonVO {

    @JsonProperty("_embedded")
    var embedded: PersonEmbeddedVO? = null
}