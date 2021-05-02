package com.nristekk.app.flickrsearchapi.main.di

import androidx.lifecycle.ViewModel
import com.nristekk.app.flickrsearchapi.di.ViewModelKey
import com.nristekk.app.flickrsearchapi.history.HistoryViewModel
import com.nristekk.app.flickrsearchapi.main.ServiceViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    //To binds service ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(ServiceViewModel::class)
    abstract fun bindServiceViewModel(serviceViewModel: ServiceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel


}