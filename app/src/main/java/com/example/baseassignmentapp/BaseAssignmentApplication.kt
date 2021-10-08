package com.example.baseassignmentapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.baseassignmentapp.modules.apiModule
import com.example.baseassignmentapp.modules.retrofitModule
import com.example.baseassignmentapp.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseAssignmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        startKoin {
            androidLogger()
            androidContext(this@BaseAssignmentApplication)

            // TODO Await fix for Koin and replace the explicit invocations
            //  of loadModules() and createRootScope() with a single call to modules()
            //  (https://github.com/InsertKoinIO/koin/issues/847)
            koin.loadModules(
                listOf(
                    viewModelModule,
                    apiModule,
                    retrofitModule
                )
            )
        }

    }
}