package com.zseni.loginscreen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zseni.loginscreen.R
import com.zseni.loginscreen.screen.components.HeadingTextComponents
import com.zseni.loginscreen.navigation.Screen

@Composable
fun TermsAndConditionsScreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)) {
        HeadingTextComponents(value = stringResource(id = R.string.terms_conditions))
    }

}