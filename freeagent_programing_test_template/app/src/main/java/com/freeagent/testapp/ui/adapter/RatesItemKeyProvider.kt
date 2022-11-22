package com.freeagent.testapp.ui.adapter

import androidx.recyclerview.selection.ItemKeyProvider
import com.freeagent.testapp.models.RatesData

class RatesItemKeyProvider(private val adapter: RatesListAdapter) :
    ItemKeyProvider<RatesData>(SCOPE_CACHED) {
    override fun getKey(position: Int): RatesData? {
        return adapter.getItem(position)
    }

    override fun getPosition(key: RatesData): Int {
        return adapter.getPosition(key.rateName!!)
    }
}