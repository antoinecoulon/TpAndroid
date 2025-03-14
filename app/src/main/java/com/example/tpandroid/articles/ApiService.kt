package com.example.tpandroid.articles

import retrofit2.http.GET

interface ApiService {
    @GET("articles") suspend fun getArticles(): Article
}