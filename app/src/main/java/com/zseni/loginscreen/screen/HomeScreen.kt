package com.zseni.loginscreen.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zseni.loginscreen.R
import com.zseni.loginscreen.data.home.HomeViewModel
import com.zseni.loginscreen.navigation.Screen
import com.zseni.loginscreen.screen.components.AppToolbar
import com.zseni.loginscreen.screen.components.NavDrawerBody
import com.zseni.loginscreen.screen.components.NavDrawerHeader
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    homeViewModel.checkForActiveSession()
    homeViewModel.getUserDataFromFirebase()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                NavDrawerHeader(homeViewModel.emailId.value)
                NavDrawerBody(navigationDrawerItems = homeViewModel.navigationItemsList,
                    onNavItemClicked = {
                        Log.d("ComingHere", "inside_NavigationItemClicked")
                        Log.d("ComingHome", "${it.itemId} ${it.title}")
                    }
                )
            }
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
                AppToolbar(toolbarTitle = stringResource(id = R.string.home),
                    logoutButtonClicked = {
                        if (homeViewModel.isUserLoggedIn.value == true){
                            navController.navigate(Screen.HomeScreen.route)
                        }
                        homeViewModel.logout()
                        navController.navigate(Screen.LoginScreen.route)
                    },
                    drawerButtonClicked = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(paddingValues)
            ) {

                Column {
//                    ButtonComponent(
//                        value = stringResource(id = R.string.logout),
//                        onButtonClicked = {
//                            navController.navigate(Screen.LoginScreen.route)
//                        },
//                        isEnabled = true
//                    )
                }
            }
        }
    }
}