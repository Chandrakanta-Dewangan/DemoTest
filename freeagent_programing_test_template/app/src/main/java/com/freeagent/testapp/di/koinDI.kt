package com.freeagent.testapp.di

import android.app.Application
import com.freeagent.testapp.db.CurrencyDatabase
import com.freeagent.testapp.db.RoomDataSource
import com.freeagent.testapp.api.CurrencyDataSource
import com.freeagent.testapp.api.CurrencyRepository
import com.freeagent.testapp.api.GetHistoricalCurrency
import com.freeagent.testapp.api.GetLatestCurrencies
import com.freeagent.testapp.api.data.LocalDataSource
import com.freeagent.testapp.api.data.RemoteDataSource
import com.freeagent.testapp.ui.activity.DetailsActivity
import com.freeagent.testapp.ui.fragment.RatesListFragment
import com.freeagent.testapp.viewmodel.CurrencyViewModel
import com.freeagent.testapp.viewmodel.HistoricalCurrencyViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { CurrencyDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { CurrencyDataSource() }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

val dataModule = module {
    factory { CurrencyRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<RatesListFragment>()) {
        viewModel { CurrencyViewModel(get(), get()) }
        scoped { GetLatestCurrencies(get()) }
    }
    scope(named<DetailsActivity>()){
        viewModel { HistoricalCurrencyViewModel(get(),get()) }
        scoped { GetHistoricalCurrency(get()) }
    }
}