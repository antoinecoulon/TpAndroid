package com.example.tpandroid.articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.tpandroid.R
import com.example.tpandroid.ui.theme.Page
import com.example.tpandroid.ui.theme.TpAndroidTheme
import com.example.tpandroid.ui.theme.TpButton
import com.example.tpandroid.ui.theme.WrapPaddingRowWeight
import java.util.logging.ErrorManager

@Composable
fun ArticleScreen(viewModel: ArticleViewModel = viewModel(factory = ArticleViewModel.Factory)) {
    val articlesResult by viewModel.articles.collectAsState()

    LaunchedEffect(Unit) { viewModel.fetchArticles() }

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
//            TpButton(buttonText = stringResource(R.string.app_btn_text_add_article), onClick = {
//                viewModel.addArticle(1, "Test", "test test test", "Antoine", "https://picsum.photos/200")
//            })
            Spacer(modifier = Modifier.weight(1f))
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                articlesResult.let { result ->
                    when {
                        result.isSuccess -> {
                            val articles = result.getOrNull() ?: emptyList()
                            items(articles) { article ->
                                Row {
                                    WrapPaddingRowWeight {
                                        Text(
                                            text = article.title,
                                            modifier = Modifier.fillMaxWidth(),
                                            color = Color(0xFFFDDFD9),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    WrapPaddingRowWeight(weight = 2f) {
                                        Text(
                                            text = article.desc,
                                            modifier = Modifier.fillMaxWidth(),
                                            color = Color(0xFFFDDFD9),
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    WrapPaddingRowWeight(weight = 2f) {
                                        AsyncImage(
                                            model = "${article.imgPath}",
                                            contentDescription = "Image ${article.title}",
                                            modifier = Modifier.fillMaxWidth().height(50.dp)
                                        )
                                    }
                                }
                            }
                        }
                        result.isFailure -> {
                            val exception = result.exceptionOrNull()
                            throw Error(exception?.message)
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