package com.hypersoft.roomdb.db.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hypersoft.roomdb.db.repository.ExampleRepository
import com.hypersoft.roomdb.db.tables.CountryTable
import kotlinx.coroutines.launch

class ExampleViewModel(private val repository: ExampleRepository) : ViewModel() {

    /***
     * Country ViewModel Methods
     */

    val allCountryList: LiveData<List<CountryTable>> = repository.allCountryList.asLiveData()

    fun insertCountry(countryTable: CountryTable) = viewModelScope.launch {
        repository.insertCountry(countryTable)
    }

    fun deleteCountry(countryTable: CountryTable) = viewModelScope.launch {
        repository.deleteCountry(countryTable)
    }

    fun updateCountry(countryTable: CountryTable) = viewModelScope.launch {
        repository.updateCountry(countryTable)
    }

    fun deleteAllCountry() = viewModelScope.launch {
        repository.deleteAllCountryList()
    }

}