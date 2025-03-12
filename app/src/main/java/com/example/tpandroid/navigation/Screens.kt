package com.example.tpandroid.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Recovery: Screens("recovery_screen")
    object SignIn: Screens("signin_screen")
    object Articles: Screens("articles_screen")
}