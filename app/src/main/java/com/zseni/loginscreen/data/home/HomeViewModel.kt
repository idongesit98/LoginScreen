package com.zseni.loginscreen.data.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.zseni.loginscreen.data.NavigationItem


class HomeViewModel:ViewModel() {
    private val TAG = HomeViewModel::class.simpleName

    val navigationItemsList = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Filled.Home,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Settings",
        icon = Icons.Filled.Settings,
        description = "Settings Screen",
        itemId = "settingsScreen"
        ),
        NavigationItem(
            title = "Favourites",
            icon = Icons.Filled.Favorite,
            description = "Favourites Screen",
            itemId = "favScreen"
        )
    )

    val isUserLoggedIn:MutableLiveData<Boolean> = MutableLiveData()
    val emailId:MutableLiveData<String> = MutableLiveData()

    fun logout(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "User sign out successful")
            } else {
                Log.d(TAG, "User sign out is not successful")
            }
        }
        firebaseAuth.addAuthStateListener { authStateListener}
    }

    fun checkForActiveSession(){
        if (FirebaseAuth.getInstance().currentUser != null){
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        }else{
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false

        }
    }

    fun getUserDataFromFirebase(){
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also {email ->
                emailId.value = email
            }
        }
    }
}
