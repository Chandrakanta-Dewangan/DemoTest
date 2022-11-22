package com.freeagent.testapp.ui.adapter

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.freeagent.testapp.ui.adapter.RatesListAdapter
import com.freeagent.testapp.models.RatesData

class RatesItemDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<RatesData>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<RatesData>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as RatesListAdapter.ViewHolder)
                .getItemDetails()
        }
        return null
    }
}