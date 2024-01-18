package com.zseni.loginscreen.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(val route:String) {
    data object SignUpScreen: Screen("SignUpScreen")
    data object TermsandConditionsScreen:Screen("TermsandConditionsScreen")
    data object LoginScreen:Screen("LoginScreen")
    data object HomeScreen:Screen("HomeScreen")
}

object LoginAppRouter{
    var currentScreen:MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination:Screen){
        currentScreen.value = destination
    }
}