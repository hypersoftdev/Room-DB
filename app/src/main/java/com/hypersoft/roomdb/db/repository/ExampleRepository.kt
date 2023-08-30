package com.hypersoft.roomdb.db.repository

import androidx.annotation.WorkerThread
import com.hypersoft.roomdb.db.daos.ExampleDao
import com.hypersoft.roomdb.db.tables.CountryTable
import kotlinx.coroutines.flow.Flow

class ExampleRepository(private val exampleDao: ExampleDao) {

    /***
     * country_table methods
     */

    val allCountryList: Flow<List<CountryTable>> = exampleDao.getAllCountryList()

    @WorkerThread
    suspend fun insertCountry(countryTable: CountryTable) {
        exampleDao.insertCountry(countryTable)
    }

    @WorkerThread
    suspend fun deleteCountry(countryTable: CountryTable) {
        exampleDao.deleteCountry(countryTable)
    }

    @WorkerThread
    suspend fun updateCountry(countryTable: CountryTable) {
        exampleDao.updateCountry(countryTable)
    }

    @WorkerThread
    suspend fun deleteAllCountryList() {
        exampleDao.deleteAllCountry()
    }
}