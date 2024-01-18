package com.zseni.loginscreen.data.signUp

sealed class SignUpUiEvent {
    data class FirstNameChanged(val firstName:String): SignUpUiEvent()
    data class LastNameChanged(val lastName:String): SignUpUiEvent()
    data class EmailChanged(val email:String): SignUpUiEvent()
    data class PasswordChanged(val password:String): SignUpUiEvent()
    data class PrivacyBoxClicked(val status:Boolean): SignUpUiEvent()

    object RegistrationButtonClicked: SignUpUiEvent()
}