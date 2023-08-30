package com.hypersoft.roomdb.interfaces

import com.hypersoft.roomdb.db.tables.CountryTable

interface OnCountryItemClickListener {
    fun onCountryClick(countryTable: CountryTable)
}