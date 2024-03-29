package com.hypersoft.roomdb.interfaces

import com.hypersoft.roomdb.db.tables.CountryTable

/**
 * @Author: Muhammad Yaqoob
 * @Date: 29,March,2024.
 * @Accounts
 *      -> https://github.com/orbitalsonic
 *      -> https://www.linkedin.com/in/myaqoob7
 */
interface OnCountryItemClickListener {
    fun onCountryClick(countryTable: CountryTable)
}