package com.hypersoft.roomdb.db.daos

import androidx.room.*
import com.hypersoft.roomdb.db.tables.CountryTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ExampleDao {
    
    /***
     * Country DAO
     */

    @Query("SELECT * FROM country_table ORDER BY id DESC")
    fun getAllCountryList(): Flow<List<CountryTable>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCountry(countryTable: CountryTable)

    @Delete
    suspend fun deleteCountry(countryTable: CountryTable)

    @Update
    suspend fun updateCountry(countryTable: CountryTable)

    @Query("DELETE FROM country_table")
    suspend fun deleteAllCountry()
}