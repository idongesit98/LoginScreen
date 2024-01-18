package com.zseni.loginscreen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zseni.loginscreen.R
import com.zseni.loginscreen.data.login.LoginUiEvent
import com.zseni.loginscreen.data.login.LoginViewModel
import com.zseni.loginscreen.screen.components.ButtonComponent
import com.zseni.loginscreen.screen.components.DividerTextComponent
import com.zseni.loginscreen.screen.components.HeadingTextComponents
import com.zseni.loginscreen.screen.components.LoginClickComponents
import com.zseni.loginscreen.screen.components.NormalTextComponents
import com.zseni.loginscreen.screen.components.PassFields
import com.zseni.loginscreen.screen.components.TextFields
import com.zseni.loginscreen.screen.components.UnderLinedText
import com.zseni.loginscreen.navigation.LoginAppRouter
import com.zseni.loginscreen.navigation.Screen

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponents(value = stringResource(id = R.string.login))
                HeadingTextComponents(value = stringResource(id = R.string.welcome_back))

                TextFields(
                    labelValue = stringResource(id = R.string.email),
                    imageVector = Icons.Outlined.Email,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUiEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUiState.value.emailError
                )

                PassFields(
                    labelValue = stringResource(id = R.string.password),
                    imageVector = Icons.Outlined.Lock,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUiEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUiState.value.passwordError
                )
                Spacer(modifier = Modifier.height(40.dp))

                UnderLinedText(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUiEvent.LoginButtonClickedButton)
                        navController.navigate(Screen.HomeScreen.route)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                DividerTextComponent()

                LoginClickComponents(tryingToLogin = false, onTextSelected = {
                    LoginAppRouter.navigateTo(Screen.SignUpScreen)
                })


            }

        }
        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }
}


@Preview
@Composable
fun LoginPreview(){
    val navController = rememberNavController()
    LoginScreen(navController)
}