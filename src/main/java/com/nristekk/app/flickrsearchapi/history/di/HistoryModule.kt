package com.nristekk.app.flickrsearchapi.history.di

import androidx.lifecycle.ViewModel
import com.nristekk.app.flickrsearchapi.di.ViewModelKey
import com.nristekk.app.flickrsearchapi.history.HistoryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HistoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindViewModel(historyViewModel: HistoryViewModel): ViewModel

}