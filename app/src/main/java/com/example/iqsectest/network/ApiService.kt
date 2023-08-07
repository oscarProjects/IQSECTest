package com.example.iqsectest.network

import com.example.iqsectest.data.model.CountryEnvelope
import com.example.iqsectest.data.model.StateEnvelope
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/GetPaises")
    @POST("Paises.asmx?op=GetPaises")
    suspend fun callCountriesService(@Body soapEnvelope: RequestBody): Response<CountryEnvelope>

    @Headers("Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/GetEstadosbyPais")
    @POST("Paises.asmx?op=GetEstadosbyPais")
    suspend fun callStatesService(@Body soapEnvelope: RequestBody): Response<StateEnvelope>
}