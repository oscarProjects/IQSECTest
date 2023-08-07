package com.example.iqsectest.network

import com.example.iqsectest.data.model.CountryEnvelope
import com.example.iqsectest.data.model.StateEnvelope
import com.example.iqsectest.di.manager.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject

class ApiProvide @Inject constructor(
    private val serviceManager: RetrofitManager
) {

    suspend fun getAllCountries(): Response<CountryEnvelope> {
        return withContext(Dispatchers.IO){
            serviceManager.callServiceGetAllCountries(buildSoapRequest("GetPaises", ""))
        }
    }

    suspend fun getAllStates(idPais: Int): Response<StateEnvelope> {
        return withContext(Dispatchers.IO){
            serviceManager.callServiceGetAllStates(buildSoapRequest("GetEstadosbyPais", "<idEstado>$idPais</idEstado>"))
        }
    }

    private fun buildSoapRequest(soapMethodName: String, soapBody: String): RequestBody {
        val soapEnvelope = """
        <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
            <soap:Body>
                <$soapMethodName xmlns="http://tempuri.org/">
                    $soapBody
                </$soapMethodName>
            </soap:Body>
        </soap:Envelope>
    """.trimIndent()

        return soapEnvelope.toRequestBody("text/xml; charset=utf-8".toMediaTypeOrNull())
    }

}