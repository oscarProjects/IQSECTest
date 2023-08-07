package com.example.iqsectest.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "Pais", strict = false)
data class Pais(
    @field:Element(name = "idPais")
    var idPais: Int = 0,

    @field:Element(name = "NombrePais")
    var nombrePais: String = ""
): Serializable

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
data class CountryBody(
    @field:Element(name = "GetPaisesResponse")
    var getPaisesResponse: GetPaisesResponse? = null
)

@Root(name = "Envelope", strict = false)
@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/")
data class CountryEnvelope(
    @field:Element(name = "Body")
    var body: CountryBody? = null
)

@Root(name = "Estado")
data class Estado(
    @field:Element(name = "idEstado")
    var idEstado: Int? = null,

    @field:Element(name = "EstadoNombre")
    var estadoNombre: String? = null,

    @field:Element(name = "Coordenadas")
    var coordenadas: String? = null,

    @field:Element(name = "idPais")
    var idPais: Int? = null
): Serializable

@Root(name = "Estados")
data class Estados(
    @field:ElementList(inline = true, required = false)
    var estados: List<Estado>? = null
)

@Root(name = "GetEstadosbyPaisResult")
data class GetEstadosbyPaisResult(
    @field:Element(name = "Estados")
    var estados: Estados? = null
)

@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
@Root(name = "Envelope")
data class StateEnvelope(
    @field:Element(name = "Body")
    var body: StateBody? = null
)

@Root(name = "Body", strict = false)
data class StateBody(
    @field:Element(name = "GetEstadosbyPaisResponse")
    var response: GetEstadosbyPaisResponse? = null
)

@Root(name = "GetEstadosbyPaisResponse", strict = false)
data class GetEstadosbyPaisResponse(
    @field:Element(name = "GetEstadosbyPaisResult")
    var result: GetEstadosbyPaisResult? = null
)