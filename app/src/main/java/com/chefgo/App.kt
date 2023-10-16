package com.chefgo

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App : MultiDexApplication() {

    // Instance of AppContainer that will be used by all the Activities of the app
    val appContainer = AppContainer()

    companion object {
        lateinit var mFirebaseAnalytics: FirebaseAnalytics
        lateinit var mFirebaseCrashlytics: FirebaseCrashlytics
    }

    override fun onCreate() {
        super.onCreate()
        initLibraries()
    }

    private fun initLibraries() {
        configFirebase()
        configStetho()
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