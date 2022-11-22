package com.freeagent.testapp.api

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.freeagent.testapp.api.data.LocalDataSource
import com.freeagent.testapp.models.Currency
import com.freeagent.testapp.models.HistoricalCurrency
import com.freeagent.testapp.api.data.RemoteDataSource
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CurrencyRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val BASE_CURRENCY: String = "EUR"
) {

    suspend fun getLatestCurrencies(): List<Currency> {
        if (localDataSource.isEmpty()) {
            val currencies =
                remoteDataSource.getLatestCurrencies(BASE_CURRENCY)
            localDataSource.saveCurrencies(currencies)
        }

        return localDataSource.getLatestCurrencies()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getHistoricalCurrencies(symbols:String): HistoricalCurrency {
        val currencies = remoteDataSource.getHistoricalCurrencies(BASE_CURRENCY,symbols,getStartDate(), getCurrentDate())
        return currencies
    }

    fun getCurrentDate(): String {
        // declaration
        var c: Calendar = Calendar.getInstance()
        var df: SimpleDateFormat? = null
        var formattedDate = ""

        // goes to main method or onCreate(Android)
        df = SimpleDateFormat("yyyy-MM-dd")
        formattedDate = df!!.format(c.time)
        Log.d("CurrentDate", "Format dateTime =>" + formattedDate)
        return formattedDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getStartDate(): String {
        val oldDate = LocalDate.now().minusDays(5)
        Log.d("CurrentDate", "Format dateTime =>" + oldDate.toString())
        return oldDate.toString()
   /* var c : Calendar = Calendar.getInstance();
        var df: SimpleDateFormat? = null
        var formattedDate = ""

        df = SimpleDateFormat("yyyy-MM-dd")
        formattedDate = df!!.format(c.time)
        Log.d("CurrentDate", "Format dateTime =>" + formattedDate)

        c.setTime(TODAY);
    //c.add(Calendar.DAY_OF_MONTH, 1) //Adds a day
     c.add(Calendar.DAY_OF_MONTH, -5) //Goes to previous day
        var yourDate = c.getTime()
        return yourDate.toString()*/
}
}