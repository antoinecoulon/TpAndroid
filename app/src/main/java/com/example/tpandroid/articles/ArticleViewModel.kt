package com.example.tpandroid.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private val _articles = MutableStateFlow<Result<List<Article>>>(Result.success(emptyList()))
    val articles: StateFlow<Result<List<Article>>> = _articles

    fun fetchArticles() {
        viewModelScope.launch {
            val result = articleRepository.fetchArticles()
            _articles.value = result
        }
    }

//    fun addArticle(id: Int, title: String, desc: String, author: String, imgPath: String? = null) {
//        val newArticle = Article(id, title, desc, author, imgPath)
//        val updatedList = _articles.value + newArticle
//        _articles.value = updatedList
//    }

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


