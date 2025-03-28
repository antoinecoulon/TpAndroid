package com.example.tpandroid.disney

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DisneyApiService {
    @GET("character") suspend fun getCharacters(
        @Query("page") page: Int = 1,
    ): Response<ApiResponse>

    object RetrofitInstance {
        private const val BASE_URL = "https://api.disneyapi.dev"

        val api: DisneyApiService by lazy {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(DisneyApiService::class.java)
        }
    }
}