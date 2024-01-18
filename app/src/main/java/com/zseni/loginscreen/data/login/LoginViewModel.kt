package com.zseni.loginscreen.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.zseni.loginscreen.data.Validator
import com.zseni.loginscreen.data.signUp.SignUpViewModel

class LoginViewModel:ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName

    val loginUiState = mutableStateOf(LoginUiState())

    var allValidationsPassed = mutableStateOf(false)

    val loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUiEvent){
        when(event){
            is LoginUiEvent.EmailChanged ->{
                loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )
            }

            is LoginUiEvent.PasswordChanged ->{
                loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
            }

            is LoginUiEvent.LoginButtonClickedButton ->{
                login()
            }
        }
        validateLoginDataWithRules()
    }


    private fun validateLoginDataWithRules(){
        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUiState.value.password
        )

        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login(){
        loginInProgress.value = true
        val email = loginUiState.value.email
        val password = loginUiState.value.password

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                loginInProgress.value = false
                Log.d(TAG,"User login Success")
                Log.d(TAG,"${it.isSuccessful}")
            }
            .addOnFailureListener {
                Log.d(TAG,"User login Success")
                Log.d(TAG, "${it.localizedMessage}")
                loginInProgress.value = false
            }

    }


}