package com.example.tpandroid.auth

data class SignupRequestAPI(
    var email: String = "",
    var password: String = "",
    var passwordConfirm: String = "",
    var pseudo: String = "",
    var cityCode: String = "",
    var city: String = "",
    var phone: String = ""
)
