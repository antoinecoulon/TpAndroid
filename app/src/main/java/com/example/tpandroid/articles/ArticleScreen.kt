package com.example.tpandroid.articles

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.tpandroid.R
import com.example.tpandroid.ui.theme.Page
import com.example.tpandroid.ui.theme.TpAndroidTheme
import com.example.tpandroid.ui.theme.WrapPadding

@Composable
fun ArticleScreen(viewModel: ArticleViewModel = viewModel()) {

    // J'écoute les changements de la liste d'articles dans le view model en temps réel
    val articlesState by viewModel.articles.collectAsState()

    Page {
        Column(modifier = Modifier.padding(32.dp)) {
            Text(
                text = stringResource(R.string.app_title_articles),
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                items(articlesState) { article ->
                    WrapPadding {
                        ElevatedCard {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                AsyncImage(
                                    model = article.imgPath,
                                    contentDescription = "Image ${article.title}",
                                    modifier = Modifier.height(96.dp)
                                )
                                Column() {
                                    Text(
                                        text = article.title,
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = article.desc,
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    TpAndroidTheme {
        ArticleScreen()
    }
}