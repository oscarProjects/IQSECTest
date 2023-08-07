package com.example.iqsectest.data.repository

import com.example.iqsectest.data.OperationResult
import com.example.iqsectest.data.model.Estado
import com.example.iqsectest.data.model.Pais
import com.example.iqsectest.network.ApiProvide
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiProvide: ApiProvide
){

    suspend fun getAllCountries(): OperationResult<Pais> {
        val response = apiProvide.getAllCountries()
        return if(response.isSuccessful && response.body() != null){
            OperationResult.Success(response.body()?.body?.getPaisesResponse?.getPaisesResult?.paises?.paises)
        }else{
            OperationResult.Error(Exception(response.errorBody().toString()))
        }
    }

    suspend fun getAllStates(idPais: Int): OperationResult<Estado> {
        val response = apiProvide.getAllStates(idPais)
        return if(response.isSuccessful && response.body() != null){
            OperationResult.Success(response.body()?.body?.response?.result?.estados?.estados)
        }else{
            OperationResult.Error(Exception(response.errorBody().toString()))
        }
    }
}