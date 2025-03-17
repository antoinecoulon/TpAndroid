package com.example.tpandroid.disney

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.tpandroid.ui.theme.TpButton

@Composable
fun CharactersScreen(viewModel: CharacterViewModel = viewModel(factory = CharacterViewModel.Factory)) {

    // Stateful
    val charactersResult by viewModel.characters.collectAsState()
    val infosResult by viewModel.infos.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
        viewModel.fetchInfos()
    }

    // Affichage
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

            // Récupération des infos -> Affichage des boutons Page suivante/précédente
            infosResult.let { result ->
                when {
                    result.isSuccess -> {
                        val info = infosResult.getOrNull()

                        if (info?.nextPage != null) {
                            val currentPage = extractPageNumberFromUrl(info.nextPage) - 1
                            Text(
                                "Page $currentPage / ${info.totalPages}",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                color = Color(0xFFFDDFD9)
                            )
                        }

                        Row {
                            if (info?.previousPage != null) {
                                TpButton(buttonText = "Page précédente", modifier = Modifier.weight(1f).padding(1.dp), onClick = {
                                    val page = extractPageNumberFromUrl(info.previousPage)
                                    viewModel.fetchCharacters(page = page)
                                })
                            }
                            if (info?.nextPage != null) {
                                TpButton(buttonText = "Page suivante", modifier = Modifier.weight(1f).padding(1.dp), onClick = {
                                    val page = extractPageNumberFromUrl(info.nextPage)
                                    viewModel.fetchCharacters(page = page)
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

            HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(4.dp), thickness = 1.dp, color = Color(0xFFFDDFD9))

            // Récupération des personnages -> Affichage par page
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                charactersResult.let { result ->
                    when {
                        result.isSuccess -> {
                            val characters = result.getOrNull() ?: emptyList()
                            items(characters) { character ->
                                Card(
                                    modifier = Modifier.padding(8.dp).border(1.dp, Color(0xFFFDDFD9)),
                                    colors = CardDefaults.cardColors(Color.Transparent)
                                ) {
                                    Text(
                                        text = character.name,
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color(0xFFFDDFD9),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold
                                    )
                                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0xFFFDDFD9))
                                    AsyncImage(
                                        model = character.imageUrl,
                                        contentDescription = "Image ${character.name}",
                                        modifier = Modifier.fillMaxWidth(),
                                        contentScale = ContentScale.Crop
                                    )
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

/**
 * Permet d'extraire le numéro de page des infos fournies par l'API
 * @params: url (ex: info.nextPage: "https://api.disneyapi.dev/character?page=5"
 */
fun extractPageNumberFromUrl(url: String): Int {
    val regex = Regex("""page=(\d+)""")
    val matchResult = regex.find(url)
    val pageNumber = matchResult?.groupValues?.get(1)?.toIntOrNull() ?: 1

    return pageNumber
}

@Preview(showBackground = true)
@Composable
fun CharactersScreenPreview() {
    TpAndroidTheme {
        CharactersScreen()
    }
}