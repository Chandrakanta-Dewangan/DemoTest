package com.freeagent.testapp.ui

import android.app.Application
import com.freeagent.testapp.R
import com.freeagent.testapp.di.initDI
import uk.co.chrisjenx.calligraphy.CalligraphyConfig





class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        initDI()
    }
}