package com.example.tpandroid.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun RecoveryScreen(navController: NavController, viewModel: AuthViewModel) {
    val recoveryAPIState by viewModel.recoveryRequestAPI.collectAsState()

    val context = LocalContext.current

    Page {
        Column(modifier = Modifier.padding(32.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.app_title_password_recovery),
                modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                fontSize = 32.sp, color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            WrapPadding {
                TpTextField(
                    value = recoveryAPIState.email,
                    onValueChange = { value -> viewModel.recoveryRequestAPI.value = viewModel.recoveryRequestAPI.value.copy(email = value) },
                    fieldText = stringResource(R.string.app_field_text_email),
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.mail),
                            contentDescription = "Mail",
                            tint = Color(0xFFFDDFD9)
                        )
                    })
            }
            WrapPadding {
                TpButton(buttonText = stringResource(R.string.app_btn_text_recovery_send_email),
                    onClick = {
                        viewModel.callRecoverRequest(
                            onRecoverSuccess = { message ->
                                navController.navigate(Screens.Home.route)
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            },
                            onRecoverFailure = { message ->
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        )
                    })
            }
            Spacer(modifier = Modifier.weight(2f))
            Text(
                text = stringResource(R.string.app_msg_dont_forget_next_time),
                color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp,
                lineHeight = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecoveryScreenPreview() {
    val viewModel = AuthViewModel()

    TpAndroidTheme {
        RecoveryScreen(navController = rememberNavController(), viewModel)
    }
}