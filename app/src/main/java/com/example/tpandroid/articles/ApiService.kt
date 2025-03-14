package com.example.tpandroid.articles

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("articles") suspend fun getArticles(): Response<ArticleResponse>
    @GET("articles/{id}") suspend fun getArticle(@Path("id") id: Int): Response<Article>

    object RetrofitInstance {
        private const val BASE_URL = "http://10.0.2.2:3000/"

        val api: ApiService by lazy {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ApiService::class.java)
        }
    }
}