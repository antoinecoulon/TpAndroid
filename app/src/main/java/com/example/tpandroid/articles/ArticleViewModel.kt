package com.example.tpandroid.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
LiveData and StateFlow can be used effectively in a ViewModel to manage and observe data,
but the choice between them depends on your specific use case and requirements
 */

class ArticleViewModel : ViewModel() {
    private val articleRepository = ArticleRepository()

    val articlesLiveData = MutableLiveData<List<Article>>()

    init {
        articlesLiveData.value = articleRepository.getArticles()
    }

    fun addArticle(title: String, desc: String, imgPath: String? = null) {
        articleRepository.addArticle(title, desc, imgPath)
        articlesLiveData.value = articleRepository.getArticles()
    }
}