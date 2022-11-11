package com.gify.theme_compose.components

import androidx.annotation.DrawableRes

data class GifItemModel(
    val gifUrl:String?=null,
    val gifName:String? = null,
    val isAnchorBarVisibe:Boolean = false,
    //@DrawableRes val anchorBarImg:Int? = null,
    val gifUrlMinHeight:Int? = null
)
