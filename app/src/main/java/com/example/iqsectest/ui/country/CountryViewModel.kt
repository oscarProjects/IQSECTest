package com.example.iqsectest.ui.country

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iqsectest.data.Country
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    var countriesList = MutableLiveData<List<Country>>()

    fun getCountries(){
        viewModelScope.launch {
            countriesList.postValue(countries())
        }
    }

    private fun countries(): MutableList<Country> {
        return mutableListOf(
            Country("1", "México"),
            Country("2", "EUA"),
            Country("3", "Colombia"),
            Country("4", "Canada"),
            Country("5", "Brasil")
        )
    }
}