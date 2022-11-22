package com.freeagent.testapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freeagent.testapp.api.GetHistoricalCurrency
import com.freeagent.testapp.models.HistoricalCurrency
import com.freeagent.testapp.utils.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HistoricalCurrencyViewModel(
    private val getCurrencies: GetHistoricalCurrency,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val uiModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (uiModel.value == null) refresh()
            return uiModel
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val currencies: HistoricalCurrency) : UiModel()
        object showUI : UiModel()

    }

    init {
        initScope()
    }

    private fun refresh() {
        uiModel.value = UiModel.showUI
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showUi(symbols: String) {
        launch {
            uiModel.value = UiModel.Loading
            uiModel.value = UiModel.Content(getCurrencies.invoke(symbols))
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}