package com.hypersoft.roomdb.db.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hypersoft.roomdb.db.convertors.Convertors
import com.hypersoft.roomdb.db.daos.ExampleDao
import com.hypersoft.roomdb.db.tables.City
import com.hypersoft.roomdb.db.tables.CountryTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [CountryTable::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class ExampleDatabase : RoomDatabase() {

    abstract fun exampleDao(): ExampleDao

    companion object {

        @Volatile
        private var INSTANCE: ExampleDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): ExampleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExampleDatabase::class.java,
                    "example_databases"
                ).addCallback(ExampleDatabaseCallback(scope, context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class ExampleDatabaseCallback(private val scope: CoroutineScope, context: Context) : Callback() {

        private val mContext = context

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    // By-Default items on database creation
                    with(database.exampleDao()) {
                        deleteAllCountry()
                        insertCountry(CountryTable(countryName = "India", countryCode = "06", city = City(cityName = "Islamabad", cityPostalCode = "51310")))
                        insertCountry(CountryTable(countryName = "Italy", countryCode = "07", city = City(cityName = "Islamabad", cityPostalCode = "51310")))
                        insertCountry(CountryTable(countryName = "Japan", countryCode = "08", city = City(cityName = "Islamabad", cityPostalCode = "51310")))
                        insertCountry(CountryTable(countryName = "Malaysia", countryCode = "09", city = City(cityName = "Islamabad", cityPostalCode = "51310")))
                    }
                }
            }
        }
    }
}