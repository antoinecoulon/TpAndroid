package com.example.tpandroid.articles

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/*
LiveData and StateFlow can be used effectively in a ViewModel to manage and observe data,
but the choice between them depends on your specific use case and requirements
 */

class ArticleViewModel : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    fun addArticle(title: String, desc: String, imgPath: String? = null) {
        var newArticle = Article(title, desc, imgPath)

        val list = _articles.value
        _articles.value = list + newArticle
    }
}