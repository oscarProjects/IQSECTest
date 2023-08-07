package com.example.iqsectest.di.manager

import com.example.iqsectest.data.model.SoapEnvelope
import com.example.iqsectest.network.ApiService
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class RetrofitManager@Inject constructor(private val apiService: ApiService) {

    suspend fun callServiceGetAllCountries(soapEnvelope: RequestBody): Response<SoapEnvelope> {
        return apiService.callSoapService(soapEnvelope)
    }
}
