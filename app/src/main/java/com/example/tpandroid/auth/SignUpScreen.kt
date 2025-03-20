package com.example.tpandroid.auth

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun SignUpScreen(navController: NavController, viewModel: AuthViewModel) {
    val requestAPIState by viewModel.signupRequestAPI.collectAsState()

    val context = LocalContext.current

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
                text = stringResource(R.string.app_msg_welcome_registration),
                modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            )
            WrapPadding {
                TpTextField(
                    value = requestAPIState.pseudo,
                    onValueChange = { value -> viewModel.signupRequestAPI.value = viewModel.signupRequestAPI.value.copy(pseudo = value) },
                    fieldText = stringResource(R.string.app_field_text_pseudo)
                )
            }
            WrapPadding {
                TpTextField(
                    value = requestAPIState.email,
                    onValueChange = { value -> viewModel.signupRequestAPI.value = viewModel.signupRequestAPI.value.copy(email = value) },
                    fieldText = stringResource(R.string.app_field_text_email)
                )
            }
            WrapPadding {
                TpTextField(
                    value = requestAPIState.password,
                    onValueChange = { value -> viewModel.signupRequestAPI.value = viewModel.signupRequestAPI.value.copy(password = value) },
                    fieldText = stringResource(R.string.app_field_text_password),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            WrapPadding {
                TpTextField(
                    value = requestAPIState.passwordConfirm,
                    onValueChange = { value -> viewModel.signupRequestAPI.value = viewModel.signupRequestAPI.value.copy(passwordConfirm = value) },
                    fieldText = stringResource(R.string.app_field_text_password_confirm),
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            WrapPadding {
                TpTextField(
                    value = requestAPIState.cityCode,
                    onValueChange = { value -> viewModel.signupRequestAPI.value = viewModel.signupRequestAPI.value.copy(cityCode = value) },
                    fieldText = stringResource(R.string.app_field_text_city_code)
                )
            }
            WrapPadding {
                TpTextField(
                    value = requestAPIState.city,
                    onValueChange = { value -> viewModel.signupRequestAPI.value = viewModel.signupRequestAPI.value.copy(city = value) },
                    fieldText = stringResource(R.string.app_field_text_city)
                )
            }
            WrapPadding {
                TpTextField(
                    value = requestAPIState.phone,
                    onValueChange = { value -> viewModel.signupRequestAPI.value = viewModel.signupRequestAPI.value.copy(phone = value) },
                    fieldText = stringResource(R.string.app_field_text_phone_number)
                )
            }
            WrapPadding {
                TpButton(
                    buttonText = stringResource(R.string.app_btn_text_sign_up),
                    onClick = {
                        viewModel.callSignupRequest(
                            onRegisterSuccess = { message ->
                                navController.navigate(Screens.Home.route)
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            },
                            onRegisterFailure = { message ->
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.app_msg_terms_service_advice),
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
    var viewModel: AuthViewModel = viewModel()
    TpAndroidTheme {
        SignUpScreen(
            navController = rememberNavController(),
            viewModel
        )
    }
}