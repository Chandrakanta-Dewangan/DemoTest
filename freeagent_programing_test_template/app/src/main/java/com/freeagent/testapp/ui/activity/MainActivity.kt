package com.freeagent.testapp.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.freeagent.testapp.R
import com.freeagent.testapp.navigation.Navigation
import com.freeagent.testapp.ui.fragment.RatesListFragment
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // supportActionBar?.hide()

        initViews()
    }

    private fun initViews() {
        initCurrencyFragment()
    }

    fun initCurrencyFragment() {
        Navigation.replaceFragment(supportFragmentManager, R.id.fragmentContainer, RatesListFragment())
    }

//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
//    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    companion object {
        private val CLASS_TAG = MainActivity::class.java.simpleName
    }
}
