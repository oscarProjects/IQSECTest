package com.example.iqsectest.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "Pais", strict = false)
data class Pais(
    @field:Element(name = "idPais")
    var idPais: Int = 0,

    @field:Element(name = "NombrePais")
    var nombrePais: String = ""
){
    override fun toString(): String {
        return idPais.toString()
    }
}

@Root(name = "Paises", strict = false)
data class Paises(
    @field:ElementList(entry = "Pais", inline = true)
    var paises: List<Pais>? = null
)

@Root(name = "GetPaisesResult", strict = false)
data class GetPaisesResult(
    @field:Element(name = "Paises")
    var paises: Paises? = null
)

@Root(name = "GetPaisesResponse", strict = false)
@Namespace(reference = "http://tempuri.org/")
data class GetPaisesResponse(
    @field:Element(name = "GetPaisesResult")
    var getPaisesResult: GetPaisesResult? = null
)

@Root(name = "Body", strict = false)
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/")
data class SoapBody(
    @field:Element(name = "GetPaisesResponse")
    var getPaisesResponse: GetPaisesResponse? = null
)

@Root(name = "Envelope", strict = false)
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/")
data class SoapEnvelope(
    @field:Element(name = "Body")
    var body: SoapBody? = null
)

