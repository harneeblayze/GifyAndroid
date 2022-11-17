package com.gify.theme_compose.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gify.theme_compose.composethemevalues.Theme

@Composable
fun LoadingItem(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
    /*CircularProgressIndicator(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .size(36.dp)
    )*/
    /*CircularProgressIndicator(
        modifier = Modifier.height(Theme.dimens.dimen_unit_1)
            .width(Theme.dimens.dimen_unit_2),
        color = Color.Blue,
        strokeWidth = Theme.dimens.dimen_unit_1
    )*/
}