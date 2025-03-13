package com.example.tpandroid.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import com.example.tpandroid.navigation.NavGraph
import com.example.tpandroid.navigation.Screens
import com.example.tpandroid.ui.theme.Page
import com.example.tpandroid.ui.theme.TpAndroidTheme
import com.example.tpandroid.ui.theme.TpButton
import com.example.tpandroid.ui.theme.TpTextField
import com.example.tpandroid.ui.theme.WrapPadding

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TpAndroidTheme {
                Surface {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(navController: NavController) {
    Page {
        Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(32.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_note),
                    contentDescription = "Registration",
                    tint = Color(0xFFFDDFD9),
                    modifier = Modifier.size(96.dp)
                )
            }
            Text(
                text = "Welcome to the registration page.",
                modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            )
            WrapPadding {
                TpTextField(fieldText = "Pseudo")
            }
            WrapPadding {
                TpTextField(fieldText = "Email")
            }
            WrapPadding {
                TpTextField(fieldText = "Password")
            }
            WrapPadding {
                TpTextField(fieldText = "Password Confirmation")
            }
            WrapPadding {
                TpTextField(fieldText = "City Code")
            }
            WrapPadding {
                TpTextField(fieldText = "City")
            }
            WrapPadding {
                TpTextField(fieldText = "Phone Number")
            }
            WrapPadding {
                TpButton(
                    buttonText = "Sign Up",
                    onClick = {navController.navigate(Screens.Home.route)}
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "By registering, I accept the Terms of service and the Privacy policy",
                modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    TpAndroidTheme {
        SignUpScreen(
            navController = rememberNavController()
        )
    }
}