package com.example.iqsectest.data.domain

import com.example.iqsectest.data.OperationResult
import com.example.iqsectest.data.model.Pais
import com.example.iqsectest.data.repository.DataRepository
import javax.inject.Inject

class CountriesUsesCase @Inject constructor(
    private val repository: DataRepository
){
    suspend operator fun invoke(): OperationResult<Pais> = repository.getAllCountries()
}