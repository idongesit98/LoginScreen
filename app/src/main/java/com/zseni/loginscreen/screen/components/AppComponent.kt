package com.zseni.loginscreen.screen.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zseni.loginscreen.R
import com.zseni.loginscreen.data.NavigationItem
import com.zseni.loginscreen.ui.theme.Accentcolour
import com.zseni.loginscreen.ui.theme.FieldsColour
import com.zseni.loginscreen.ui.theme.GrayColour
import com.zseni.loginscreen.ui.theme.Pink40
import com.zseni.loginscreen.ui.theme.Pink80
import com.zseni.loginscreen.ui.theme.Textcolour
import com.zseni.loginscreen.ui.theme.background
import com.zseni.loginscreen.ui.theme.componentShapes
import com.zseni.loginscreen.ui.theme.focusedColour
import kotlinx.coroutines.launch

@Composable
fun NormalTextComponents(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal
        ),
        color = Textcolour,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponents(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal
        ),
        color = Textcolour,
        textAlign = TextAlign.Center
    )
}


@Composable
fun TextFields(labelValue:String, imageVector:ImageVector,
               onTextSelected: (String) -> Unit,
               errorStatus:Boolean = false
               ){
    val textValue = remember{
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue)},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = focusedColour,
            focusedLabelColor = focusedColour,
            cursorColor = focusedColour,
            focusedContainerColor = FieldsColour,
            unfocusedContainerColor = FieldsColour
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(id = R.string.profile_img))
        },
        isError = !errorStatus

    )
}

@Composable
fun PassFields(
    labelValue:String,
    imageVector:ImageVector, onTextSelected: (String) -> Unit, errorStatus:Boolean = false
){
    val localFocusManager = LocalFocusManager.current
    val password = remember{
        mutableStateOf("")
    }

    val passwordVisible = remember{
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = labelValue)},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = focusedColour,
            focusedLabelColor = focusedColour,
            cursorColor = focusedColour,
            focusedContainerColor = FieldsColour,
            unfocusedContainerColor = FieldsColour
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions { localFocusManager.clearFocus()},
        singleLine = true,
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(id = R.string.profile_img))
        },
        trailingIcon = {
            val iconImage = if(passwordVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus

    )
}

@Composable
fun CheckboxComponents(value: String, onTextSelected: (String) -> Unit,
                       onCheckedChanged:(Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChanged.invoke(it)
            }
        )
       ClickableComponents(value = value, onTextSelected)

    }
}

@Composable
fun ClickableComponents(value: String, onTextSelected: (String) -> Unit){
    val initialText = "By continuing you accept our"
    val privacyPolicyText = " Privacy Policy "
    val andText = " and  "
    val termsAndConditionText = " Terms of Use "
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(
            style = SpanStyle(color = Textcolour)){
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(
            style = SpanStyle(color = Textcolour)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(termsAndConditionText)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")

                if(span.item == termsAndConditionText || (span.item == privacyPolicyText)){
                    onTextSelected(span.item)
                }
            }

       }
    )
}

@Composable
fun ButtonComponent(value:String, onButtonClicked: () -> Unit, isEnabled:Boolean){
   Button(
       onClick = { onButtonClicked.invoke()},
       modifier = Modifier
           .fillMaxWidth()
           .heightIn(48.dp),
       contentPadding = PaddingValues(),
       colors = ButtonDefaults.buttonColors(Color.Transparent),
       shape = RoundedCornerShape(50.dp),
       enabled = isEnabled
   ) {
       Box(modifier = Modifier
           .fillMaxWidth()
           .heightIn(48.dp)
           .background(
               brush = Brush.horizontalGradient(listOf(Accentcolour, Textcolour)),
               shape = RoundedCornerShape(50.dp)
           ),
           contentAlignment = Alignment.Center
       ){
           Text(
               text = value,
               fontSize = 18.sp,
               color = Color.White,
               fontWeight = FontWeight.Bold
               )

       }

   }

}

@Composable
fun DividerTextComponent(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = GrayColour,
            thickness = 1.dp
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.or), fontSize = 18.sp, color = Textcolour)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = GrayColour,
            thickness = 1.dp
        )


    }
}

@Composable
fun LoginClickComponents( tryingToLogin:Boolean, onTextSelected:(String) -> Unit){
    val initialText = if (tryingToLogin) "Already have an account? " else " Don't have an account yet? "
    val loginText = if (tryingToLogin) " Login " else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(
            style = SpanStyle(color = Accentcolour)){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")

                if(span.item == loginText){
                  onTextSelected(span.item)
                }
            }

    }
    )
}

@Composable
fun UnderLinedText(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal
        ),
        color = GrayColour,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(toolbarTitle:String, logoutButtonClicked:() -> Unit, drawerButtonClicked:  () -> Unit){

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Pink80
        ),
        title = {
            Text(text = toolbarTitle, color = Color.White)
        },
        navigationIcon = {
            IconButton(
                onClick = { drawerButtonClicked.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.menu),
                    modifier = Modifier.clickable {  },
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                logoutButtonClicked() //.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout ,
                    contentDescription = stringResource(id = R.string.logout)
                )
            }

        }
    )

}

@Composable
fun NavDrawerHeader(value: String?){
    val shadowOffset = Offset(4f,6f)
    Box(modifier =
    Modifier
        .fillMaxWidth()
        .padding(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.navigation_header),
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal,
                shadow = Shadow(
                    color = Pink40,
                    offset = shadowOffset, 2f
                )
            )
        )

    }
}

@Composable
fun NavDrawerBody(navigationDrawerItems:List<NavigationItem>,onNavItemClicked:(NavigationItem) -> Unit){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        content = {
            items(navigationDrawerItems){
                NavigationItemRow(
                    item = it,
                    onNavItemClicked
                    )
            }
        }
    )
}

@Composable
fun NavigationItemRow(item: NavigationItem, onNavItemClicked:(NavigationItem) -> Unit){
    val shadowOffset = Offset(4f,6f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavItemClicked.invoke(item) }
            .padding(all = 16.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.description,
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = item.title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                shadow = Shadow(
                    color = Pink40,
                    offset = shadowOffset, 2f
                )
            )
        )

    }
}