package com.example.tpandroid.articles

import com.example.tpandroid.articles.RetrofitTools.Companion.retrofit
import retrofit2.http.GET

interface ArticleService {
    @GET("articles")
    suspend fun getArticles(): ResponseAPI<List<Article>>
//    @GET("articles/{id}") suspend fun getArticle(@Path("id") id: Int): Response<Article>

    object ArticleApi {
        val articleService : ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}