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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gify.mobimeoappchallenge.viewmodel.GifViewModel
import com.gify.theme_compose.components.AppBar
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
                    //Greeting("Android")
                    GifListScreen(viewmodel = viewModel)
                    //GifComposeContent(viewModel)
                }
            }
        }
    }
}



@Composable
fun GifComposeContent(viewModel: GifViewModel){
    Column() {
        AppBar(title = "Gify Compose")
        GifListScreen(viewmodel = viewModel )
    }

}

