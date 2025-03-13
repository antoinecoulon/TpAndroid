package com.example.tpandroid.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    init {
        loadArticles()
    }

    private fun loadArticles() {
        val loadedArticles = articleRepository.getArticles()
        _articles.value = loadedArticles
    }

    fun addArticle(title: String, desc: String, imgPath: String? = null) {
        val newArticle = Article(title, desc, imgPath)
        val updatedList = _articles.value + newArticle
        _articles.value = updatedList
    }

    // ViewModel Factory
    companion object {
        val Factory:ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get Application Object from Extras...
                val application = checkNotNull(extras[APPLICATION_KEY])

                return ArticleViewModel(ArticleRepository()) as T
            }
        }
    }
}


