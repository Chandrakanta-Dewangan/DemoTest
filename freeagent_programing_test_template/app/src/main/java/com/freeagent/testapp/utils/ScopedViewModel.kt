package com.freeagent.testapp.utils

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.freeagent.testapp.utils.Scope
import kotlinx.coroutines.CoroutineDispatcher

abstract class ScopedViewModel(uiDispatcher: CoroutineDispatcher) : ViewModel(),
    Scope by Scope.Impl(uiDispatcher) {

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}