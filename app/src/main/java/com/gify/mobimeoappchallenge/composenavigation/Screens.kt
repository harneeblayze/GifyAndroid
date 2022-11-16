package com.gify.mobimeoappchallenge.composenavigation

sealed class Screens(val route:String){
    object List: Screens("list_screen")
    object Detail: Screens("Detail_screen")
}
