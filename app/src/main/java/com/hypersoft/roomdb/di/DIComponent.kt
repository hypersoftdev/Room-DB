package com.hypersoft.roomdb.di

import com.hypersoft.roomdb.db.viewmodel.ExampleViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * @Author: Muhammad Yaqoob
 * @Date: 29,March,2024.
 * @Accounts
 *      -> https://github.com/orbitalsonic
 *      -> https://www.linkedin.com/in/myaqoob7
 */
class DIComponent : KoinComponent {

    // ViewModels
    val exampleViewModel by inject<ExampleViewModel>()
}