package com.example.tpandroid.disney

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import kotlin.math.log

@Composable
fun CharactersScreen(viewModel: CharacterViewModel = viewModel(factory = CharacterViewModel.Factory)) {
    val charactersResult by viewModel.characters.collectAsState()
    val infosResult by viewModel.infos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
        viewModel.fetchInfos()
    }

    Page {
        Column(modifier = Modifier.padding(32.dp)) {

            Text(
                text = stringResource(R.string.app_title_disney_characters),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                color = Color(0xFFFDDFD9),
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )

            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color(0xFFFDDFD9))

            infosResult.let { result ->
                when {
                    result.isSuccess -> {
                        val info = infosResult.getOrNull()
                        Row {
                            WrapPaddingRowWeight {
                                TpButton(buttonText = "Page précédente", onClick = {
                                    info?.previousPage?.let { url ->
                                        val page = extractPageNumberFromUrl(url)
                                        viewModel.fetchCharacters(page = page)
                                    }
                                })
                                TpButton(buttonText = "Page suivante", onClick = {
                                    info?.nextPage?.let { url ->
                                        val page = extractPageNumberFromUrl(url)
                                        viewModel.fetchCharacters(page = page)
                                    }
                                })
                            }
                        }
                    }
                    result.isFailure -> {
                        val exception = result.exceptionOrNull()
                        throw Error(exception?.message)
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(0xFFFDDFD9))

            LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                charactersResult.let { result ->
                    when {
                        result.isSuccess -> {
                            val characters = result.getOrNull() ?: emptyList()
                            items(characters) { character ->
                                Row {
                                    Text(
                                        text = character.name,
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color(0xFFFDDFD9),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row {
                                    AsyncImage(
                                        model = character.imageUrl,
                                        contentDescription = "Image ${character.name}",
                                        modifier = Modifier.fillMaxWidth().height(100.dp).border(1.dp, Color.Black)
                                    )
                                }
                                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0xFFFDDFD9))
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

fun extractPageNumberFromUrl(url: String): Int {
    val regex = Regex("""page=(\d+)""")
    val matchResult = regex.find(url)
    val pageNumber = matchResult?.groupValues?.get(1)?.toIntOrNull() ?: 1

    Log.d("", "${pageNumber}")

    return pageNumber
}

@Preview(showBackground = true)
@Composable
fun CharactersScreenPreview() {
    TpAndroidTheme {
        CharactersScreen()
    }
}