#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integrationtests.vo

import jakarta.xml.bind.annotation.XmlRootElement
import java.util.*

@XmlRootElement
data class BookVO (

    var id: Long = 0,
    var author: String = "",
    var launchDate: Date? = null,
    var price: Double = 0.0,
    var title: String = ""
)