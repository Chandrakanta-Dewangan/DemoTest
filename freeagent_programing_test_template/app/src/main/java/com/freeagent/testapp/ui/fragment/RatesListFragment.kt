package com.freeagent.testapp.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freeagent.testapp.R
import com.freeagent.testapp.ui.adapter.RatesItemDetailsLookup
import com.freeagent.testapp.ui.adapter.RatesItemKeyProvider
import com.freeagent.testapp.models.RatesData
import com.freeagent.testapp.ui.activity.DetailsActivity
import com.freeagent.testapp.ui.adapter.RatesListAdapter
import com.freeagent.testapp.utils.inflate
import com.freeagent.testapp.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.fragment_rates_list.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class RatesListFragment : Fragment() {

    private lateinit var mRatesListAdapter: RatesListAdapter
    private lateinit var activityApp: AppCompatActivity
    private lateinit var amount: String
    private var mSelectedRatesData: MutableList<RatesData> = mutableListOf()
    private var mRatesList: List<RatesData> = mutableListOf()
    private var mTracker: SelectionTracker<RatesData>? = null
    private val mCurrencyViewModel: CurrencyViewModel by currentScope.viewModel(this)
    private var actionMode: ActionMode? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        activityApp = getActivity() as AppCompatActivity
        return container?.inflate(R.layout.fragment_rates_list)
    }


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rateslistRV.layoutManager = mLayoutManager
        rateslistRV.setHasFixedSize(true)

        mRatesListAdapter = RatesListAdapter(mRatesList)
        rateslistRV.adapter = mRatesListAdapter
        mTracker = SelectionTracker.Builder<RatesData>(
            "mySelection",
            rateslistRV,
            RatesItemKeyProvider(mRatesListAdapter),
            RatesItemDetailsLookup(rateslistRV),
            StorageStrategy.createParcelableStorage(RatesData::class.java)
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        mRatesListAdapter.mTracker = mTracker

        mTracker?.addObserver(object : SelectionTracker.SelectionObserver<RatesData>() {
            override fun onSelectionChanged() {
                super.onSelectionChanged()
                mTracker?.let {
                    val size:Int? = mTracker?.selection?.size()
                    mSelectedRatesData = it.selection.toMutableList()
                    Log.d("Fragment: ","onSelectionChanged RateList size :"+mSelectedRatesData.size.toString());
                    if (size == 0) {
                        actionMode?.finish()
                    } else {
                        if (actionMode == null) actionMode =
                            activityApp.startSupportActionMode(actionModeCallback)
                        actionMode?.title = size.toString()
                    }
                }
            }
        })
        mCurrencyViewModel.model.observe(this, Observer(::updateUi))

    }

    private val actionModeCallback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.action_view_delete -> {
                    val intent = Intent(activity, DetailsActivity::class.java)

                    intent.putExtra ("amount", amount.toInt())
                    intent.putParcelableArrayListExtra("SelectedRatesData",ArrayList( mSelectedRatesData))
                    startActivity(intent)
                }
            }
            return true
        }
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.let {
                val inflater: MenuInflater = it.menuInflater
                inflater.inflate(R.menu.action_mode_menu, menu)
                return true
            }
            return false
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            mRatesListAdapter.mTracker?.clearSelection()
            mRatesListAdapter.notifyDataSetChanged()
            actionMode = null
        }
    }

    private fun updateUi(model: CurrencyViewModel.UiModel) {
        containerProgressIndicator.visibility =
            if (model is CurrencyViewModel.UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is CurrencyViewModel.UiModel.Content -> {
                mRatesListAdapter.currencies = model.currencies
                for (i in 0 until mRatesListAdapter.currencies.size) {
                    mRatesListAdapter.rates = mRatesListAdapter.currencies[i].rates!!
                }
                mRatesList = mRatesListAdapter.rates!!

                amount = amountEditText.text.toString()
                amountEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                    override fun onEditorAction(
                        text: TextView,
                        actionId: Int,
                        event: KeyEvent?
                    ): Boolean {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || actionId == EditorInfo.IME_ACTION_SEND
                            || event?.action == KeyEvent.ACTION_DOWN
                            && event.keyCode == KeyEvent.KEYCODE_ENTER
                        ) {

                            amount = text.text.toString()

                            if (amount.isEmpty()) {
                                return true
                            }

                            mRatesListAdapter.setAmount(amount.toInt())
                            mRatesListAdapter.notifyDataSetChanged()
                            hideKeyboard(requireActivity())
                            return true
                        }
                        return true
                    }
                })
            }
            CurrencyViewModel.UiModel.showUI -> {
                mCurrencyViewModel.showUi()
            }
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}