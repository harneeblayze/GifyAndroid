package com.gify.theme_compose.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gify.theme_compose.composethemevalues.Theme

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes navigationIcon: Int? = null,
    navigationAction: (() -> Unit)? = null,

) {

    Column(modifier = modifier.background(Color.Blue).fillMaxWidth().height(
        Theme.dimens.dimen_unit_4)) {
        navigationIcon?.let {
            IconButton(onClick = { navigationAction?.invoke() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = it),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        Text(text = title, style = TextStyle(
            color = Color.White, fontSize = 16.sp),
            modifier = modifier.padding(start = 16.dp),
            textAlign = TextAlign.Center
        )
    }

    }
