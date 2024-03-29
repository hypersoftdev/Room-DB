package com.hypersoft.roomdb.db.tables

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Author: Muhammad Yaqoob
 * @Date: 29,March,2024.
 * @Accounts
 *      -> https://github.com/orbitalsonic
 *      -> https://www.linkedin.com/in/myaqoob7
 */
@Parcelize
data class City(
    var cityName: String,
    var cityPostalCode: String
) : Parcelable