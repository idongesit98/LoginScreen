package com.zseni.loginscreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zseni.loginscreen.screen.HomeScreen
import com.zseni.loginscreen.screen.LoginScreen
import com.zseni.loginscreen.screen.SignupScreen

@Composable
fun LoginGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Screen.SignUpScreen.route){

        composable(
            route = Screen.SignUpScreen.route
        ){
            SignupScreen(navController)
        }

        composable(
            route = Screen.LoginScreen.route
        ){
            LoginScreen(navController)
        }

        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController)
        }


    }


}