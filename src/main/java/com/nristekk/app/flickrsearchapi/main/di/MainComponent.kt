package com.nristekk.app.flickrsearchapi.main.di

import com.nristekk.app.flickrsearchapi.main.MainActivity
import com.nristekk.app.flickrsearchapi.main.MainFragment
import dagger.Subcomponent


@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)

    // Here goes for the main Fragment
    fun inject(mainfragment: MainFragment)


}

