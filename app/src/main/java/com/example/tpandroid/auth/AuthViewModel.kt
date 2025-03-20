package com.example.tpandroid.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.articles.AppDialogHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(): ViewModel() {

    // Servira à lier les données dans les champs et à envoyer en JSON dans l'api
    var loginRequestAPI = MutableStateFlow(LoginRequestAPI())
    var signupRequestAPI = MutableStateFlow(SignupRequestAPI())
    var recoveryRequestAPI = MutableStateFlow(RecoveryRequestAPI())

    // onLoginSuccess est un CALLBACK
    fun callLoginRequest(onLoginSuccess: (message: String) -> Unit = {}, onLoginFailure: (message: String) -> Unit = {}) {

        AppDialogHelper.get().showDialog("Verifying credentials...")

        viewModelScope.launch(Dispatchers.IO) {

            // Appel API /login avec dans le body mon requestAPI (donc email + pwd)
            val apiResponse = AuthService.AuthApi.authService.login(loginRequestAPI.value)

            AppDialogHelper.get().closeDialog()

            when (apiResponse.code) {
                "200" -> {
                    AuthService.token = apiResponse.data!!

                    withContext(Dispatchers.Main) {
                        // REDIRECT !
                        // Le viewModel doit être réutilisable et donc indépendant de la navigation
                        // à la place on utilise le Callback
                        onLoginSuccess("${apiResponse.code}: ${apiResponse.message}")
                    }
                }
                "768" -> {
                    withContext(Dispatchers.Main) {
                        onLoginFailure("${apiResponse.code}: ${apiResponse.message}")
                    }
                }
            }
        }
    }

    fun callSignupRequest(onRegisterSuccess: (message: String) -> Unit = {}, onRegisterFailure: (message: String) -> Unit = {}) {

        AppDialogHelper.get().showDialog("Please wait...")

        viewModelScope.launch {
            val apiResponse = AuthService.AuthApi.authService.signup(signupRequestAPI.value)

            AppDialogHelper.get().closeDialog()

            when (apiResponse.code) {
                "200" -> {
                    // TODO: Popup confirm
                    var newUser = User(
                        email = apiResponse.data!!.email,
                        password = apiResponse.data!!.password,
                        pseudo = apiResponse.data!!.pseudo,
                        cityCode = apiResponse.data!!.cityCode,
                        city = apiResponse.data!!.city,
                        phone = apiResponse.data!!.phone
                    )
                    println(apiResponse.message)

                    withContext(Dispatchers.Main) {
                        onRegisterSuccess("${apiResponse.code}: ${apiResponse.message}")
                    }
                }
                "712" -> {
                    withContext(Dispatchers.Main) {
                        onRegisterFailure("${apiResponse.code}: ${apiResponse.message}")
                    }
                }
            }
        }
    }

    fun callRecoverRequest(onRecoverSuccess: (message: String) -> Unit = {}, onRecoverFailure: (message: String) -> Unit = {}) {
        AppDialogHelper.get().showDialog("Verifying email...")

        viewModelScope.launch(Dispatchers.IO) {
            val apiResponse = AuthService.AuthApi.authService.recover(recoveryRequestAPI.value)

            AppDialogHelper.get().closeDialog()

            when (apiResponse.code) {
                "200" -> {
                    var newPassword = apiResponse.data!!
                    println("Password modified, new password is $newPassword")

                    withContext(Dispatchers.Main) {
                        onRecoverSuccess("${apiResponse.code}: ${apiResponse.message}")
                    }
                }
                else -> {
                    withContext(Dispatchers.Main) {
                        onRecoverFailure("${apiResponse.code}: ${apiResponse.message}")
                    }
                }
            }
        }
    }
}