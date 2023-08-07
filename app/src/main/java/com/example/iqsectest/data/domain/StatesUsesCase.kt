package com.example.iqsectest.data.domain

import com.example.iqsectest.data.OperationResult
import com.example.iqsectest.data.model.Estado
import com.example.iqsectest.data.repository.DataRepository
import javax.inject.Inject

class StatesUsesCase @Inject constructor(
    private val repository: DataRepository
){
    suspend fun getStates(idPais: Int): OperationResult<Estado> = repository.getAllStates(idPais)
}