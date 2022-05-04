package br.com.semeru.integrationtests.vo.wrappers

import com.fasterxml.jackson.annotation.JsonProperty

class WrapperBookVO {

    @JsonProperty("_embedded")
    var embedded: BookEmbeddedVO? = null
}