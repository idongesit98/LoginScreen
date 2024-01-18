package com.zseni.loginscreen.data.login

data class LoginUiState(
    val email:String = "",
    val password:String = "",
    val emailError:Boolean = false,
    val passwordError: Boolean = false,
)
