package com.example.tpandroid.articles

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleViewModel : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    fun addArticle(title: String, desc: String, imgPath: String? = null) {
        var newArticle = Article(title, desc, imgPath)

        val list = _articles.value
        _articles.value = list + newArticle
    }
}