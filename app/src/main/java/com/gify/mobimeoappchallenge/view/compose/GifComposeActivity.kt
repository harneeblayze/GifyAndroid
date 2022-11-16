package com.gify.mobimeoappchallenge.view.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.compose.rememberNavController
import com.gify.mobimeoappchallenge.composenavigation.NavGraph
import com.gify.mobimeoappchallenge.viewmodel.GifViewModel
import com.gify.theme_compose.components.AppBar
import com.gify.theme_compose.components.ErrorItem
import com.gify.theme_compose.components.SearchView
import com.gify.theme_compose.composethemevalues.MobimeoAppChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifComposeActivity : ComponentActivity() {
    private val viewModel: GifViewModel by viewModels()
    //private val viewModel by viewModels<GifViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobimeoAppChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*val navController = rememberNavController()
                    NavGraph(navController = navController, viewModel)*/
                    //EmptyGifState()
                    //GifListScreen(viewmodel = viewModel)
                    GifComposeContent(viewModel)
                }
            }
        }
    }
}



@Composable
fun GifComposeContent(viewModel: GifViewModel){
    /*val textState = remember { mutableStateOf(TextFieldValue("")) }
    viewModel.queryChange(textState.value.text)*/
    Column() {
        TopAppBar(
            title = {
                Text(text = "Browse Gif List")
            }
        )
        /*SearchView(state = textState)
        if (textState.value.text.isBlank()){
        EmptyGifState()}else{
            GifListScreen(viewmodel = viewModel )
        }*/
        GifListScreen(viewModel = viewModel)
    }

}

