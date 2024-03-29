package com.hypersoft.roomdb.di

import com.hypersoft.roomdb.db.db.ExampleDatabase
import com.hypersoft.roomdb.db.repository.ExampleRepository
import com.hypersoft.roomdb.db.viewmodel.ExampleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @Author: Muhammad Yaqoob
 * @Date: 29,March,2024.
 * @Accounts
 *      -> https://github.com/orbitalsonic
 *      -> https://www.linkedin.com/in/myaqoob7
 */
private val applicationScope = CoroutineScope(SupervisorJob())

private val dbModule = module {
    single { ExampleDatabase.getDatabase(androidContext(), applicationScope).exampleDao() }

    single { ExampleRepository(get()) }

    single { ExampleViewModel(get()) }
}

val modulesList = listOf(dbModule)