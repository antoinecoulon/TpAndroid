package com.example.tpandroid.articles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.tpandroid.navigation.NavGraph
import com.example.tpandroid.ui.theme.Page
import com.example.tpandroid.ui.theme.TpAndroidTheme
import com.example.tpandroid.ui.theme.TpButton
import com.example.tpandroid.ui.theme.WrapPaddingRowWeight

class ArticleActivity : ComponentActivity() {
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

val articleRepository = ArticleRepository()

@Composable
fun ArticleScreen() {
    val articles = articleRepository.getArticles()

    Page {
        Column(modifier = Modifier.padding(32.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Articles",
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
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
                                model = "https://picsum.photos/200",
                                contentDescription = "Image ${article.title}",
                                modifier = Modifier.fillMaxWidth().height(50.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            TpButton(buttonText = "Ajouter un article", onClick = {
                articleRepository.addArticle("Test", "test test test")
            })
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