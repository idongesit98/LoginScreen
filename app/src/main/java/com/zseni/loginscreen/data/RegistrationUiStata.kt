package com.zseni.loginscreen.data

data class RegistrationUiState(
    val firstName:String = "",
    val lastName:String = "",
    val email:String = "",
    val password:String = "",
    val firstNameError:Boolean = false,
    val lastNameError:Boolean = false,
    val emailError:Boolean = false,
    val passwordError:Boolean = false,
    val privacyPolicyAccepted:Boolean = false,
    val privacyPolicyError:Boolean = false
)
