package com.nristekk.app.flickrsearchapi

import android.app.Application
import com.nristekk.app.flickrsearchapi.di.AppComponent
import com.nristekk.app.flickrsearchapi.di.DaggerAppComponent


/*
App class provide DI components, in this app
we are using Dagger as DI library
 */
open class FlickrSearchApp : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        /*
        * Operation for getting initial-start data get
        * would be operated here
        */

        if (BuildConfig.DEBUG){
            //Timber.plant(DebugTree())
        }

    }
}