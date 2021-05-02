package com.nristekk.app.flickrsearchapi.di

import android.content.Context
import com.nristekk.app.flickrsearchapi.data.repos.HistoryRepos
import com.nristekk.app.flickrsearchapi.history.di.HistoryComponent
import com.nristekk.app.flickrsearchapi.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AppModule::class
            ,AppModuleBinds::class
            ,ViewModelBuilderModule::class
            ,SubcomponentsModule::class
        ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    //Components that yielded from Factory /created functions/ or Bind
    fun mainComponent(): MainComponent.Factory
    fun historyComponent(): HistoryComponent.Factory
    val historyRepos: HistoryRepos

}


@Module(
        subcomponents = [
            MainComponent::class,
            HistoryComponent::class
        ]
)
object SubcomponentsModule