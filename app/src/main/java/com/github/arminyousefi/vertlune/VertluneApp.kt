package com.github.arminyousefi.vertlune

import android.app.Application
import com.github.arminyousefi.vertlune.data.local.DatabaseInitializer
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class VertluneApp : Application() {

    @Inject
    lateinit var databaseInitializer: DatabaseInitializer

    override fun onCreate() {
        super.onCreate()

        // اجرای عملیات Seeding در کوروتین
        CoroutineScope(Dispatchers.IO).launch {
            databaseInitializer.populateData()
        }
    }
}