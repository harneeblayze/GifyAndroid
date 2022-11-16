package com.gify.mobimeoappchallenge.composenavigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gify.mobimeoappchallenge.view.compose.GifDetailScreen
import com.gify.mobimeoappchallenge.view.compose.GifListScreen
import com.gify.mobimeoappchallenge.viewmodel.GifViewModel


@Composable
fun NavGraph (navController: NavHostController, viemodel:GifViewModel){
    NavHost(
        navController = navController,
        startDestination = Screens.List.route
    )
    {

        composable(route = Screens.List.route){
            //HomeScreen()
            GifListScreen(viewModel = viemodel )
        }
        composable(route = Screens.Detail.route){
            //DetailScreen()
            GifDetailScreen()
        }
    }
}