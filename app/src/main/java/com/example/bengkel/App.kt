package com.example.bengkel

import android.app.Application
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.persistence.GanderPersistence
import com.example.bengkel.di.databaseModule
import com.example.bengkel.di.networkModule
import com.example.bengkel.di.repositoryModule
import com.example.bengkel.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Gander.setGanderStorage(GanderPersistence.getInstance(this))

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}