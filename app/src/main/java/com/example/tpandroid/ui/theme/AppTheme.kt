package com.example.tpandroid.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tpandroid.R
import com.example.tpandroid.articles.ProgressDialog

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
                ProgressDialog()
            }
        }
    }
}

// Text field
@Composable
fun TpTextField(
    fieldText : String = "",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    icon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = fieldText, color = Color(0xFFFDDFD9), fontSize = 16.sp)
        },
        textStyle = TextStyle(color = Color(0xFFFDDFD9)),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        leadingIcon = icon,
        visualTransformation = visualTransformation
    )
}

// Buttons
@Composable
fun TpButton(buttonText: String, modifier: Modifier = Modifier, onClick: () -> Unit){
    Button(
        modifier = modifier,
        onClick = onClick,
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

@Composable
fun RowScope.WrapPaddingRowWeight(modifier: Modifier = Modifier, weight: Float = 1f, content: @Composable () -> Unit){
    Box(modifier = Modifier.padding(8.dp).weight(weight)) {
        content()
    }
}
