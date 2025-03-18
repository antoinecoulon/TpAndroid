package com.example.tpandroid.articles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    var articles = MutableStateFlow<List<Article>>(mutableListOf())

    fun loadArticles() {

        // Méthode pour traduire le string en dehors du Composable, nécessite de changer les params de ArticleViewModel, NavGraph, du preview, etc...
        val message = getApplication<Application>().getString(R.string.app_dialog_text_loading_articles)

        AppDialogHelper.get().showDialog(message)

        viewModelScope.launch {

            // Simuler 1 sec de lag en dev pour voir la popup
            delay(1000)

            // Maintenant le retour de l'API est un code, un message et le data
            val apiResponse = ArticleService.ArticleApi.articleService.getArticles()

            // On attend que la tâche ASYNC soit terminée
            AppDialogHelper.get().closeDialog()

            // Afficher le message
            // TODO: pour l'instant le message est dans la console
            println(apiResponse.message)

            // Tester si OK (code == 200)
            if(apiResponse.code.equals("200")) {
                articles.value = apiResponse.data!!
            }
        }
    }

// ViewModel Factory
//    companion object {
//        val Factory:ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//                // Get Application Object from Extras...
//                val application = checkNotNull(extras[APPLICATION_KEY])
//
//                return ArticleViewModel(ArticleRepository()) as T
//            }
//        }
//    }
}


