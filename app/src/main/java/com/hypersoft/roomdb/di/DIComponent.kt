package com.hypersoft.roomdb.di

import com.hypersoft.roomdb.db.viewmodel.ExampleViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DIComponent : KoinComponent {

    // ViewModels
    val exampleViewModel by inject<ExampleViewModel>()
}