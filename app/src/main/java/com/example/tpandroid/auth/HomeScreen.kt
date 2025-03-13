package com.example.tpandroid.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tpandroid.R
import com.example.tpandroid.navigation.Screens
import com.example.tpandroid.ui.theme.Page
import com.example.tpandroid.ui.theme.TpAndroidTheme
import com.example.tpandroid.ui.theme.TpButton
import com.example.tpandroid.ui.theme.TpTextField
import com.example.tpandroid.ui.theme.WrapPadding

@Composable
fun HomeScreen(navController: NavController) {
    Page {
        Column(modifier = Modifier.padding(32.dp)) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "Face",
                    modifier = Modifier.align(Alignment.Center).size(96.dp),
                    tint = Color(0xFFFDDFD9)
                )
            }
            WrapPadding {
                Text(
                    text = "Please be aware when you enter credentials in the login page.",
                    modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                    color = Color(0xFFFDDFD9),
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic
                )
            }
            WrapPadding {
                TpTextField(
                    fieldText = "Email",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.mail),
                            contentDescription = "mail",
                            tint = Color(0xFFFDDFD9)
                        )
                    }
                )
            }
            WrapPadding {
                TpTextField(
                    fieldText = "Password",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.key),
                            contentDescription = "key",
                            tint = Color(0xFFFDDFD9)
                        )
                    }
                )
            }
            WrapPadding {
                TpButton(
                    buttonText = "I forgot my password !",
                    onClick = {navController.navigate(Screens.Recovery.route)}
                )
            }
            WrapPadding {
                TpButton(
                    buttonText = "Sign In",
                    onClick = {navController.navigate(Screens.Articles.route)}
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            WrapPadding {
                Text(
                    text = "Don't have an account ?",
                    color = Color(0xFFFDDFD9),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            }
            WrapPadding {
                TpButton(
                    buttonText = "Register now !",
                    onClick = {navController.navigate(Screens.SignUp.route)}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TpAndroidTheme {
        HomeScreen(navController = rememberNavController())
    }
}