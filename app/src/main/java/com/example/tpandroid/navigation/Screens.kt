package com.example.tpandroid.navigation

sealed class Screens(val route: String) {
    object Home: Screens("home_screen")
    object Recovery: Screens("recovery_screen")
    object SignUp: Screens("signup_screen")
    object Articles: Screens("articles_screen")

    // Disney
    object Characters: Screens("characters_screen")
}