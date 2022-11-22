package com.freeagent.testapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.freeagent.testapp.db.dao.CurrencyDAO
import com.freeagent.testapp.db.dao.RateDAO
import com.freeagent.testapp.db.model.Currency
import com.freeagent.testapp.db.model.Rate
import com.freeagent.testapp.db.model.RatesTypeConverter

@Database(entities = [Currency::class, Rate::class], version = 1, exportSchema = false)
@TypeConverters(RatesTypeConverter::class)
abstract class CurrencyDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            "currency-db"
        ).build()
    }

    abstract fun currencyDao(): CurrencyDAO
    abstract fun rateDao(): RateDAO
}