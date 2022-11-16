package com.gify.mobimeoappchallenge.view.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.gify.mobimeoappchallenge.R

@Composable
fun EmptyGifState(){
    // to keep track if the animation is playing
// and play pause accordingly
    var isPlaying by remember {
        mutableStateOf(true)
    }

// for speed
    /*var speed by remember {
        mutableStateOf(1f)
    }*/

    // remember lottie composition, which
// accepts the lottie composition result
    val composition by rememberLottieComposition(

        LottieCompositionSpec
            // here `code` is the file name of lottie file
            // use it accordingly
            .RawRes(R.raw.astronaut)
    )

    // to control the animation
    val progress by animateLottieCompositionAsState(
        // pass the composition created above
        composition,

        // Iterates Forever
        iterations = LottieConstants.IterateForever,

        // pass isPlaying we created above,
        // changing isPlaying will recompose
        // Lottie and pause/play
        isPlaying = isPlaying,

        // pass speed we created above,
        // changing speed will increase Lottie
        //speed = speed,

        // this makes animation to restart
        // when paused and play
        // pass false to continue the animation
        // at which is was paused
        restartOnPlay = false

    )


    Column(Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {


        // LottieAnimation
        // Pass the composition
        // and the progress state
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier.size(250.dp)
                /*.fillMaxWidth()
                .wrapContentHeight()*/
        )

        //Spacer(modifier = Modifier.size(8.dp))

        Text(text = stringResource(id = R.string.empty_state_text),
        textAlign = TextAlign.Center, style = TextStyle(color =
            Color(R.color.primaryLight), fontSize = 16.sp, fontFamily = FontFamily.Cursive
            )
        )



}
}