package com.zseni.loginscreen.data.signUp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.zseni.loginscreen.data.RegistrationUiState
import com.zseni.loginscreen.data.Validator

class SignUpViewModel:ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName

    val registrationUiState = mutableStateOf(RegistrationUiState())
    val allValidationPassed = mutableStateOf(false)
    val signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignUpUiEvent){
        validateDataWithRules()
        when(event){
            is SignUpUiEvent.FirstNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignUpUiEvent.LastNameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignUpUiEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignUpUiEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignUpUiEvent.RegistrationButtonClicked ->{
                signUp()
            }

            is SignUpUiEvent.PrivacyBoxClicked ->{
                registrationUiState.value = registrationUiState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d( TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = registrationUiState.value.email,
            password = registrationUiState.value.password
        )
    }

    private fun validateDataWithRules() {
        val firstNameResult = Validator.validateFirst(
            firstName = registrationUiState.value.firstName
        )

        val lastNameResult = Validator.validateLast(
            lastName = registrationUiState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUiState.value.email
        )

        val passWordResult = Validator.validatePassword(
            password = registrationUiState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUiState.value.privacyPolicyAccepted
        )
        Log.d( TAG, "Inside_validateDataWithRules")
        Log.d( TAG, "firstNameResults = $firstNameResult")
        Log.d( TAG, "lastNameResults = $lastNameResult")
        Log.d( TAG, "emailResult = $emailResult")
        Log.d( TAG, "passwordResult = $passWordResult")
        Log.d( TAG, "privacyPolicyResult = $privacyPolicyResult")

        registrationUiState.value = registrationUiState.value.copy(
            firstNameError = firstNameResult.status,
            lastNameError = lastNameResult.status,
            emailError = emailResult.status,
            passwordError = passWordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )
        allValidationPassed.value = firstNameResult.status && lastNameResult.status
                && emailResult.status && passWordResult.status && privacyPolicyResult.status
//        if (firstNameResult.status && lastNameResult.status && emailResult.status && passWordResult.status){
//            allValidationPassed.value = true
//        }else{
//            allValidationPassed.value = false
//        }
    }

    private fun printState(){
        Log.d( TAG, "Inside_printState")
        Log.d(TAG, registrationUiState.value.toString())

    }

    private fun createUserInFirebase(email: String, password: String) {
        signUpInProgress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                signUpInProgress.value = false
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }

    fun logout(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = AuthStateListener{
            if(it.currentUser == null){
              Log.d(TAG,"User sign out successful")
            }else{
                Log.d(TAG,"User sign out is not successful")
            }
        }

        firebaseAuth.addAuthStateListener { authStateListener}
    }
}