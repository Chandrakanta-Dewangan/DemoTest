package com.freeagent.testapp.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.freeagent.testapp.R
import com.freeagent.testapp.models.HistoricalCurrency
import com.freeagent.testapp.models.HistoricalRate
import com.freeagent.testapp.models.RatesData
import com.freeagent.testapp.utils.round
import com.freeagent.testapp.viewmodel.HistoricalCurrencyViewModel
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var mHistoricalCurrencies: HistoricalCurrency
    private lateinit var symbols:String
    private lateinit var mSelectedRates:List<RatesData>
    private var mAmount:Int = 0
    private val mHistoryViewModel: HistoricalCurrencyViewModel by currentScope.viewModel(this)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)
        mHistoryViewModel.model.observe(this, Observer(::updateUi))
        mAmount = intent.extras!!.getInt("amount")!!
        mSelectedRates = intent.extras!!.getParcelableArrayList<RatesData>("SelectedRatesData")!!

        if (mSelectedRates != null) {
            symbols = mSelectedRates.joinToString(separator = ",") { it -> "${it.rateName}" }
        }
    }


@RequiresApi(Build.VERSION_CODES.O)
fun updateUi(uiModel: HistoricalCurrencyViewModel.UiModel?) {
    containerProgressIndicator.visibility =
        if (uiModel is HistoricalCurrencyViewModel.UiModel.Loading) View.VISIBLE else View.GONE

    when (uiModel) {
        is HistoricalCurrencyViewModel.UiModel.Content -> {
            mHistoricalCurrencies = uiModel.currencies
            var mapData: Map<String,List<HistoricalRate>>? = mHistoricalCurrencies.rates
            //First Row
            val tr = TableRow(this)
            val dateTv = TextView(this)
            dateTv.setText("Dates")
            tr.addView(dateTv)

            var isTrue = true;
            mapData!!.forEach { (key, value) ->
                Log.d("Fragment ","Key and value"+key+"::::: "+value)

                //2nd, 3rd...Row
                val tr1 = TableRow(this)
                val txtGeneric = TextView(this)
                txtGeneric.setText(key)
                tr1.addView(txtGeneric)

                val historicalVal = value
                //For Column
                for (j in 0 until historicalVal.size) {
                    if(historicalVal[j].rateVal!! > 0) {
                        //For Column label
                        if(isTrue) {
                        val txtGeneric = TextView(this)
                        txtGeneric.setText(historicalVal[j].rateName)
                        tr.addView(txtGeneric)
                       }

                        //For Column Value
                        val txtGeneric1 = TextView(this)
                        if(mAmount == 0) {
                            txtGeneric1.setText((historicalVal[j].rateVal!!.toDouble()).round(2).toString())
                        }else{
                            txtGeneric1.setText((mAmount.toDouble() * historicalVal[j].rateVal!!).round(2).toString())
                        }
                        tr1.addView(txtGeneric1)
                    }
            }
                if(isTrue) {
                tableLayout.addView(tr)
                    isTrue = false
                }
                tableLayout.addView(tr1)
            }

        }
        HistoricalCurrencyViewModel.UiModel.showUI -> {
            mHistoryViewModel.showUi(symbols)
        }
    }
}

}
