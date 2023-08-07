package com.example.iqsectest.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqsectest.data.OperationResult
import com.example.iqsectest.data.domain.StatesUsesCase
import com.example.iqsectest.data.model.Estado
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val statesUsesCase: StatesUsesCase) : ViewModel() {

    private val _states = MutableLiveData<List<Estado>>().apply { value = emptyList() }
    val statesList: LiveData<List<Estado>> = _states

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun getStates(idPais: Int){
        viewModelScope.launch {
            val result: OperationResult<Estado> = withContext(Dispatchers.IO) {
                statesUsesCase.getStates(idPais)
            }

            when (result) {
                is OperationResult.Success -> {
                    if (result.data.isNullOrEmpty()) {
                        _isEmptyList.value = true
                    } else {
                        _states.value = result.data
                    }
                }
                is OperationResult.Error -> {
                    _onMessageError.value = result.exception?:Exception("Ocurrió un error")
                }
            }
        }
    }

    fun parseLatLongFromString(inputString: String): Pair<Double, Double>? {
        val latLongValues = inputString.split(",")
        if (latLongValues.size != 2) {
            return null
        }

        try {
            val latitude = latLongValues[0].trim().toDouble()
            val longitude = latLongValues[1].trim().toDouble()
            return Pair(latitude, longitude)
        } catch (e: NumberFormatException) {
            // Error converting string to double
            return null
        }
    }
}