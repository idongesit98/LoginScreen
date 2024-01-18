package com.zseni.loginscreen

import android.app.Application
import com.google.firebase.FirebaseApp

class LoginScreen:Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}