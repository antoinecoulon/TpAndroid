package com.example.tpandroid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.tpandroid.navigation.Screens
import com.example.tpandroid.ui.theme.Page
import com.example.tpandroid.ui.theme.TpAndroidTheme
import com.example.tpandroid.ui.theme.TpButton
import com.example.tpandroid.ui.theme.TpTextField
import com.example.tpandroid.ui.theme.WrapPadding

@Composable
fun RecoveryScreen(navController: NavController) {
    Page {
        Column(modifier = Modifier.padding(32.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Password Recovery",
                modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                fontSize = 32.sp, color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            WrapPadding {
                TpTextField(
                    fieldText = "Email",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.mail),
                            contentDescription = "Mail",
                            tint = Color(0xFFFDDFD9)
                        )
                    })
            }
            WrapPadding {
                TpButton(buttonText = "Send me an email !", onClick = {navController.navigate(Screens.Home.route)})
            }
            Spacer(modifier = Modifier.weight(2f))
            Text(
                text = "Next time don't forget your password dude !",
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
    TpAndroidTheme {
        RecoveryScreen(navController = rememberNavController())
    }
}