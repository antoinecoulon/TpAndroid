package com.example.tpandroid.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.articles.AppDialogHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(): ViewModel() {

    // Servira à lier les données dans les champs et à envoyer en JSON dans l'api
    var requestAPI = MutableStateFlow(RequestAPI())

    // onLoginSuccess est un CALLBACK
    fun callLoginRequest(onLoginSuccess: () -> Unit = {}) {

        AppDialogHelper.get().showDialog("Verifying credentials...")

        viewModelScope.launch {
            delay(1000)

            // Appel API /login avec dans le body mon requestAPI (donc email + pwd)
            val apiResponse = AuthService.AuthApi.authService.login(requestAPI.value)

            AppDialogHelper.get().closeDialog()

            when (apiResponse.code) {
                "200" -> {
                    AuthService.token = apiResponse.data!!

                    AppDialogHelper.get().showDialog("Login successful, preparing articles...")

                    // REDIRECT !
                    // Le viewModel doit être réutilisable et donc indépendant de la navigation
                    // à la place on utilise le Callback
                    onLoginSuccess()

                    AppDialogHelper.get().closeDialog()
                }
                "768" -> {
                    AppDialogHelper.get().showDialog("Error: Wrong credentials. Please try again.")
                    delay(1000)
                    AppDialogHelper.get().closeDialog()
                }
            }
        }
    }
}