package com.example.tpandroid.articles

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Result

class ArticleRepository {
//    private val apiService: ApiService = ApiService.RetrofitInstance.api

//    suspend fun fetchArticles(): Result<List<Article>> {
//        return withContext(Dispatchers.IO) {
//            try {
//                val response = apiService.getArticles()
//                if (response.isSuccessful) {
//                    val articleResponse = response.body()
//                    Result.success(articleResponse?.data ?: emptyList())
//                } else {
//                    Result.failure(Exception("Error: ${response.errorBody()}"))
//                }
//            } catch (e: Exception) {
//                Result.failure(e)
//            }
//        }
//    }

//    fun addArticle(id: Int, title: String, desc: String, author: String, imgPath: String? = null) {
//        // Créer une instance d'Article
//        val newArticle = Article(
//            id = id,
//            title = title,
//            desc = desc,
//            author = author,
//            imgPath = imgPath ?: "default_image"
//        )
//        articles.add(newArticle)
//    }
}