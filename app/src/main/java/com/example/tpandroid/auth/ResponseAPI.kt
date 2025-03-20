package com.example.tpandroid.auth

data class ResponseAPI<T>(var code: String = "", var message: String = "", var data: String?) {
}