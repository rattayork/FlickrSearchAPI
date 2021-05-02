package com.nristekk.app.flickrsearchapi.history.di

import com.nristekk.app.flickrsearchapi.history.HistoryFragment
import com.nristekk.app.flickrsearchapi.main.MainActivity
import com.nristekk.app.flickrsearchapi.main.di.MainComponent
import com.nristekk.app.flickrsearchapi.main.di.MainModule
import dagger.Subcomponent


@Subcomponent(modules = [HistoryModule::class])
interface HistoryComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HistoryComponent
    }

    // Classes that can be injected by this Component
    fun inject(historyFragment: HistoryFragment)


}