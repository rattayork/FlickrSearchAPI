package com.nristekk.app.flickrsearchapi.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nristekk.app.flickrsearchapi.api.FlickrSearchApi
import com.nristekk.app.flickrsearchapi.data.AppDatabase
import com.nristekk.app.flickrsearchapi.data.entity.HistoryDao
import com.nristekk.app.flickrsearchapi.data.populateDatabaseScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Singleton

/*
* Dagger App Module object, containing especially Provided instances
*/
@Module
object AppModule {

    lateinit var DbINSTANCE:AppDatabase

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppScope() = CoroutineScope(SupervisorJob())


    //To Provide Database and do Pre-populated at once.
    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(context: Context, appScope:CoroutineScope): AppDatabase {
        DbINSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "code_challenge_sap.db"
        ).fallbackToDestructiveMigration()
                .addCallback(object: RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //Do pre-polulate in BG Thread (Coroutine Scope)
                        DbINSTANCE ?.let { database ->
                            appScope.launch(Dispatchers.IO) {
                                //Do pre-populated here for marketing reason
                                populateDatabaseScope(database.historyDao())
                            }
                        }
                    }
                }).build()
        return DbINSTANCE
    }

    /*
    To Provide Flickr photo search APi
     */
    @JvmStatic
    @Singleton
    @Provides
    fun provideFlickrSearchApi():FlickrSearchApi{
        return FlickrSearchApi.create()
    }



    //To provides History
    @JvmStatic
    @Singleton
    @Provides
    fun provideHistoryDao(database: AppDatabase):HistoryDao{
        return database.historyDao()
    }




}