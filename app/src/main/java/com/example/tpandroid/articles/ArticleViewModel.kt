package com.example.tpandroid.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel() : ViewModel() {

    var articles = MutableStateFlow<List<Article>>(
        mutableListOf()
    )

    fun loadArticles() {
        //articles.value = mutableListOf(Article("Test", "Test", ""))
        viewModelScope.launch {
            val apiResponse = ArticleService.ArticleApi.articleService.getArticles()

            articles.value = apiResponse
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


