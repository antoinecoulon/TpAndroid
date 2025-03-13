package com.example.tpandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.tpandroid.articles.ArticleScreen
import com.example.tpandroid.auth.HomeScreen
import com.example.tpandroid.auth.RecoveryScreen
import com.example.tpandroid.auth.SignUpScreen

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.Home.route){
        composable(route = Screens.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screens.Recovery.route){
            RecoveryScreen(navController)
        }
        composable(route = Screens.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = Screens.Articles.route){
            ArticleScreen()
        }
    }
}