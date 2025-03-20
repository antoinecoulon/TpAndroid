package com.example.tpandroid.auth

import com.example.tpandroid.auth.RetrofitTools.Companion.retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    companion object {
      // Token stocké dans l'app, si redémarre, est supprimé
      var token : String = ""
    }

    @POST("login")
    suspend fun login(@Body requestAPI: RequestAPI): ResponseAPI<String>

    object AuthApi {
        val authService : AuthService by lazy { retrofit.create(AuthService::class.java) }
    }
}