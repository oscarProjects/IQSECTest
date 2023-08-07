package com.example.iqsectest.di.manager

import com.example.iqsectest.data.model.CountryEnvelope
import com.example.iqsectest.data.model.StateEnvelope
import com.example.iqsectest.network.ApiService
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class RetrofitManager@Inject constructor(private val apiService: ApiService) {

    suspend fun callServiceGetAllCountries(soapEnvelope: RequestBody): Response<CountryEnvelope> {
        return apiService.callCountriesService(soapEnvelope)
    }

    suspend fun callServiceGetAllStates(soapEnvelope: RequestBody): Response<StateEnvelope> {
        return apiService.callStatesService(soapEnvelope)
    }
}
