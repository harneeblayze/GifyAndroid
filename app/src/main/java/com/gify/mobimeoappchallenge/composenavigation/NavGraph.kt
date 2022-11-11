package com.gify.mobimeoappchallenge.composenavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    )
    {

        composable(route = Screens.Home.route){
            //HomeScreen()
        }
        composable(route = Screens.Detail.route){
            //DetailScreen()
        }
    }
}