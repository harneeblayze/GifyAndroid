package com.gify.theme_compose.composethemevalues

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Immutable
data class Dimens(
    val dimen_unit_0: Dp = 0.dp,
    val dimen_unit_1: Dp = 8.dp,
    val dimen_unit_1_75: Dp = 14.dp,
    val dimen_unit_2: Dp = 16.dp,
    val dimen_unit_2_5: Dp = 20.dp,
    val dimen_unit_4: Dp = 32.dp,
    val dimen_unit_25: Dp = 200.dp,
    val dimen_unit_50: Dp = 400.dp
   /* val dimen_text_unit_1_5: = 12.sp,
    val dimen_unit_0: Dp = 0.dp,
    val dimen_unit_0: Dp = 0.dp,*/
)
internal val localDimens = staticCompositionLocalOf { Dimens() }
