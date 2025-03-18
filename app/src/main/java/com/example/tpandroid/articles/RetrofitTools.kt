package com.example.tpandroid.articles

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitTools {

    companion object {
        val BASE_URL = "https://raw.githubusercontent.com/Chocolaterie/EniWebService/refs/heads/main/api/"

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).build()
    }
}