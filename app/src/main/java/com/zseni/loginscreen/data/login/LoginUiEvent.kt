package com.zseni.loginscreen.data.login

sealed class LoginUiEvent {
    data class EmailChanged(val email:String): LoginUiEvent()
    data class PasswordChanged(val password:String): LoginUiEvent()

    object LoginButtonClickedButton: LoginUiEvent()
}