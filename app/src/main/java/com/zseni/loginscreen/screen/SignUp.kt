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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.twotone.Lock
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
import com.zseni.loginscreen.data.signUp.SignUpViewModel
import com.zseni.loginscreen.data.signUp.SignUpUiEvent
import com.zseni.loginscreen.screen.components.ButtonComponent
import com.zseni.loginscreen.screen.components.CheckboxComponents
import com.zseni.loginscreen.screen.components.DividerTextComponent
import com.zseni.loginscreen.screen.components.HeadingTextComponents
import com.zseni.loginscreen.screen.components.LoginClickComponents
import com.zseni.loginscreen.screen.components.NormalTextComponents
import com.zseni.loginscreen.screen.components.PassFields
import com.zseni.loginscreen.screen.components.TextFields
import com.zseni.loginscreen.navigation.Screen

@Composable
fun SignupScreen(navController: NavController, signUpViewModel: SignUpViewModel = viewModel()){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponents(value = stringResource(id = R.string.hello))
                HeadingTextComponents(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))
                TextFields(
                    labelValue = stringResource(id = R.string.first_name),
                    imageVector = Icons.Outlined.Person,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.FirstNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.firstNameError
                )
                TextFields(
                    labelValue = stringResource(id = R.string.last_name),
                    imageVector = Icons.Outlined.Person,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.LastNameChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.lastNameError
                )
                TextFields(
                    labelValue = stringResource(id = R.string.email),
                    imageVector = Icons.Outlined.Email,
                    onTextSelected = {
                        signUpViewModel.onEvent((SignUpUiEvent.EmailChanged(it)))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.emailError
                )
                PassFields(
                    labelValue = stringResource(id = R.string.password),
                    imageVector = Icons.TwoTone.Lock,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUiEvent.PasswordChanged(it))
                    },
                    errorStatus = signUpViewModel.registrationUiState.value.passwordError
                )

                CheckboxComponents(
                    value = stringResource(id = R.string.terms_conditions),
                    onTextSelected = {
                        navController.navigate(Screen.LoginScreen.route)
                    },
                    onCheckedChanged = {
                        signUpViewModel.onEvent(SignUpUiEvent.PrivacyBoxClicked(it))
                    }

                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signUpViewModel.onEvent(SignUpUiEvent.RegistrationButtonClicked)
                        navController.navigate(Screen.HomeScreen.route)
                    },
                    isEnabled = signUpViewModel.allValidationPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                LoginClickComponents(tryingToLogin = true, onTextSelected = {
                    navController.navigate(Screen.LoginScreen.route)
                })

            }
        }

        if (signUpViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun PreviewSignUp(){
    val navController = rememberNavController()
    SignupScreen(navController)
}