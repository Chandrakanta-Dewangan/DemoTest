package com.freeagent.testapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.freeagent.testapp.R
import com.freeagent.testapp.models.Currency
import com.freeagent.testapp.models.RatesData
import com.freeagent.testapp.utils.basicDiffUtil
import com.freeagent.testapp.utils.inflate
import com.freeagent.testapp.utils.round

class RatesListAdapter(ratesList: List<RatesData>) : RecyclerView.Adapter<RatesListAdapter.ViewHolder>() {
    var mTracker: SelectionTracker<RatesData>? = null
    private lateinit var mholder: ViewHolder
    private var mAmount:Int = 0
    var rates: List<RatesData> = ratesList
    init {
        setHasStableIds(true)
    }

    var currencies: MutableList<Currency> by basicDiffUtil(
        mutableListOf(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )
    fun setAmount(amount: Int) {
        if (amount != 0) {
            mAmount = amount
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_view_currency, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mholder = holder
        mTracker?.let {
            holder.setCurrency(rates!![position],it.isSelected(rates[position]))
        }
    }

    override fun getItemCount(): Int {
        return rates?.size!!
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var currencyTitle: TextView
        private var currencyVal: TextView
        /* private var currencyGbpTitle: TextView
         private var currencyBrlTitle: TextView*/

        init {
            super.itemView
            currencyTitle = itemView.findViewById(R.id.titleTextView)
            currencyVal = itemView.findViewById(R.id.valTextView)
        }
        fun setCurrency(curr: RatesData, isActivated: Boolean = false) {
            itemView.isActivated = isActivated
            Log.d("Fragment: ","setCurrency :"+itemView.isActivated );
            mholder.bind(curr)
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<RatesData> =
            object : ItemDetailsLookup.ItemDetails<RatesData>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): RatesData? = rates[position]
            }

        @SuppressLint("SetTextI18n")
        fun bind(rates: RatesData) {
            currencyTitle.text = rates.rateName.toString()
            if(mAmount ==0) {
                currencyVal.text = rates.rateVal?.round(2).toString()
            }else{
                currencyVal.text = "${(mAmount.toDouble() * rates.rateVal!!).round(2)}"
            }
        }
    }
    fun getItem(position: Int) = rates[position]
    fun getPosition(name: String) = rates. indexOfFirst{ it.rateName == name }

    override fun getItemId(position: Int): Long {
        Log.d("Fragment: ","getItemId :"+position.toString());
        return position.toLong()
    }
}