package com.example.iqsectest.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqsectest.data.OperationResult
import com.example.iqsectest.data.domain.CountriesUsesCase
import com.example.iqsectest.data.model.Pais
import com.example.iqsectest.data.model.Paises
import com.example.iqsectest.data.model.SoapEnvelope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countriesUsesCase: CountriesUsesCase) : ViewModel() {

    private val _countries = MutableLiveData<List<Pais>>().apply { value = emptyList() }
    val countriesList: LiveData<List<Pais>> = _countries

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun getCountries(){
        viewModelScope.launch {
            val result: OperationResult<Pais> = withContext(Dispatchers.IO) {
                countriesUsesCase.invoke()
            }

            when (result) {
                is OperationResult.Success -> {
                    if (result.data.isNullOrEmpty()) {
                        _isEmptyList.value = true
                    } else {
                        _countries.value = result.data
                    }
                }
                is OperationResult.Error -> {
                    _onMessageError.value = result.exception?:Exception("Ocurrió un error")
                }
            }
        }
    }
}