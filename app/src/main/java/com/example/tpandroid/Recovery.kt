package com.example.tpandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tpandroid.ui.theme.Page
import com.example.tpandroid.ui.theme.TpAndroidTheme
import com.example.tpandroid.ui.theme.TpButton
import com.example.tpandroid.ui.theme.TpTextField

class RecoveryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TpAndroidTheme {
                RecoveryPage()
            }
        }
    }
}

@Composable
fun RecoveryPage() {
    Page {
        Column(modifier = Modifier.padding(32.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Password Recovery",
                modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
                fontSize = 32.sp, color = Color.LightGray,
                textAlign = TextAlign.Center
            )
            TpTextField(fieldText = "Email")
            TpButton(buttonText = "Send me an email!")
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Next time don't forget your password dude !"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecoveryPagePreview() {
    TpAndroidTheme {
        RecoveryPage()
    }
}