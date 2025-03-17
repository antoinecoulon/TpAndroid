package com.example.tpandroid.articles

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ArticleViewModel() : ViewModel() {

    var articles = MutableStateFlow<List<Article>>(
        mutableListOf(
            Article("Chaussette", "Todo", "https://picsum.photos/200/300"),
            Article("T-Shirt", "Todo", "https://picsum.photos/200/300")
        )
    )

//    private val _articles = MutableStateFlow<Result<List<Article>>>(Result.success(emptyList()))
//    val articles: StateFlow<Result<List<Article>>> = _articles
//
//    fun fetchArticles() {
//        viewModelScope.launch {
//            val result = articleRepository.fetchArticles()
//            _articles.value = result
//        }
//    }

//    fun addArticle(id: Int, title: String, desc: String, author: String, imgPath: String? = null) {
//        val newArticle = Article(id, title, desc, author, imgPath)
//        val updatedList = _articles.value + newArticle
//        _articles.value = updatedList
//    }

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


