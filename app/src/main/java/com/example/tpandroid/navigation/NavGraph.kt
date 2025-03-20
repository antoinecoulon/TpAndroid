package com.example.tpandroid.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.tpandroid.articles.ArticleScreen
import com.example.tpandroid.articles.ArticleViewModel
import com.example.tpandroid.auth.AuthViewModel
import com.example.tpandroid.auth.HomeScreen
import com.example.tpandroid.auth.RecoveryScreen
import com.example.tpandroid.auth.SignUpScreen
import com.example.tpandroid.disney.CharactersScreen

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(route = Screens.Home.route){
            HomeScreen(navController, viewModel = AuthViewModel())
        }
        composable(route = Screens.Recovery.route){
            RecoveryScreen(navController)
        }
        composable(route = Screens.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = Screens.Articles.route){
            ArticleScreen(viewModel = ArticleViewModel(application = LocalContext.current.applicationContext as Application))
        }
        composable(route = Screens.Characters.route) {
            CharactersScreen()
        }
    }
}