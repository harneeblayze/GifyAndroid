package com.gify.mobimeoappchallenge.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import com.gify.theme_compose.components.ComposeGifItemComponent
import com.gify.theme_compose.components.GifItemModel


@Composable
fun GifDetailScreen(){
    ComposeGifItemComponent(model = GifItemModel()) {

    }
}