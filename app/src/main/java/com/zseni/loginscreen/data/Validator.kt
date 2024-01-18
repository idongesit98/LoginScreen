package com.zseni.loginscreen.data

object Validator {
    fun validateFirst(firstName:String):ValidationResult{
        return ValidationResult(
            (!firstName.isNullOrEmpty() && firstName.length >= 2)
        )
    }

    fun validateLast(lastName:String):ValidationResult{
        return ValidationResult(
            (!lastName.isNullOrEmpty() && lastName.length >= 2)
        )
    }

    fun validateEmail(email:String):ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePassword(password:String):ValidationResult{
        return ValidationResult(
            (!password.isEmpty() && password.length >= 4)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue:Boolean):ValidationResult{
        return ValidationResult(
            statusValue
        )

    }
}

data class ValidationResult(
    val status:Boolean = false
)