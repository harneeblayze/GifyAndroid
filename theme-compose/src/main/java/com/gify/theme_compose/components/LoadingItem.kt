package com.gify.theme_compose.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gify.theme_compose.composethemevalues.Theme

@Composable
fun LoadingItem(){
    CircularProgressIndicator(
        modifier = Modifier.height(Theme.dimens.dimen_unit_1)
            .width(Theme.dimens.dimen_unit_2),
        color = Color.Blue,
        strokeWidth = Theme.dimens.dimen_unit_1
    )
}