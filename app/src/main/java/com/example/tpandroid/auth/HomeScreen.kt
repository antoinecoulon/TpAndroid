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
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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

class HomeActivity : ComponentActivity() {
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
fun HomeScreen(navController: NavController, viewModel: AuthViewModel) {
    // On observe l'objet qui contient l'email et le mot de passe
    val requestAPIState by viewModel.requestAPI.collectAsState()

    Page {
        Column(modifier = Modifier.padding(32.dp)) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Icon(
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "face",
                    modifier = Modifier.align(Alignment.Center).size(96.dp),
                    tint = Color(0xFFFDDFD9)
                )
            }
            WrapPadding {
                Text(
                    text = stringResource(R.string.app_msg_credentials_aware),
                    modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                    color = Color(0xFFFDDFD9),
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic
                )
            }
            WrapPadding {

                TpTextField(
                    value = requestAPIState.email,
                    onValueChange = { value -> viewModel.requestAPI.value = viewModel.requestAPI.value.copy(email = value) },
                    fieldText = stringResource(R.string.app_field_text_email),
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
                    value = requestAPIState.password,
                    onValueChange = { value -> viewModel.requestAPI.value = viewModel.requestAPI.value.copy(password = value) },
                    fieldText = stringResource(R.string.app_field_text_password),
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.key),
                            contentDescription = "key",
                            tint = Color(0xFFFDDFD9)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            WrapPadding {
                TpButton(
                    buttonText = stringResource(R.string.app_btn_text_forgot_password),
                    onClick = {navController.navigate(Screens.Recovery.route)}
                )
            }
            WrapPadding {
                TpButton(
                    buttonText = stringResource(R.string.app_btn_text_sign_in),
                    onClick = {
                        viewModel.callLoginRequest(
                            onLoginSuccess = { navController.navigate(Screens.Articles.route) }
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            WrapPadding {
                TpButton(
                    buttonText = stringResource(R.string.app_btn_text_disney),
                    onClick = {navController.navigate(Screens.Characters.route)}
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            WrapPadding {
                Text(
                    text = stringResource(R.string.app_msg_no_account),
                    color = Color(0xFFFDDFD9),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            }
            WrapPadding {
                TpButton(
                    buttonText = stringResource(R.string.app_btn_text_register),
                    onClick = {navController.navigate(Screens.SignUp.route)}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val viewModel = AuthViewModel()
    TpAndroidTheme {
        HomeScreen(navController = rememberNavController(), viewModel)
    }
}