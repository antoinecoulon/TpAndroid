package com.example.tpandroid.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tpandroid.R

// Main theme
@Composable
fun Page(content: @Composable () -> Unit) {
    TpAndroidTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Image(
                    painter = painterResource(R.drawable.background),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                content()
            }
        }
    }
}

// Text field
@Composable
fun TpTextField(fieldText : String = "", icon: @Composable (() -> Unit)? = null){
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = {},
        placeholder = {
            Text(text = fieldText, color = Color(0xFFFDDFD9), fontSize = 16.sp)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        leadingIcon = icon
    )
}

// Buttons
@Composable
fun TpButton(buttonText : String){
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        contentPadding = PaddingValues(),
        border = BorderStroke(1.dp, Color(0xFFFDDFD9))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(brush = Brush.linearGradient(
                listOf(Color(0xFFFF5722), Color(0xFF673AB7))
            )).fillMaxWidth().padding(vertical = 12.dp)
        ){
            Text(text = buttonText, fontSize = 16.sp)
        }
    }
}

@Composable
fun WrapPadding(content: @Composable () -> Unit){
    Box(modifier = Modifier.padding(4.dp)) {
        content()
    }
}