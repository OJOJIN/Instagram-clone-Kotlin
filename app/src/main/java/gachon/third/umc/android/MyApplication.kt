package gachon.third.umc.android

import android.app.Application

class MyApplication : Application() {
    companion object {
        lateinit var sharedPrefs: PreferenceUtil
    }

    override fun onCreate() {
        sharedPrefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}