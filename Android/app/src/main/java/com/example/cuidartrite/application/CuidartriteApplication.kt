package com.example.cuidartrite.application

import android.app.Application
import com.example.cuidartrite.datastore.UserDataStore

class CuidartriteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserDataStore.initialize(this)
    }
}