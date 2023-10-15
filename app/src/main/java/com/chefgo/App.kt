package com.chefgo

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.paperdb.Paper

class App : MultiDexApplication() {

    companion object {
        lateinit var mFirebaseAnalytics: FirebaseAnalytics
        lateinit var mFirebaseCrashlytics: FirebaseCrashlytics
    }

    override fun onCreate() {
        super.onCreate()
        initLibraries()
    }

    private fun initLibraries() {
        configPaper()
        configFirebase()
        configStetho()
    }

    private fun configPaper() {
        Paper.init(this)
    }

    private fun configFirebase() {
        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mFirebaseCrashlytics = FirebaseCrashlytics.getInstance()
    }

    private fun configStetho() {
        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }

}