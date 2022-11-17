package com.gify.theme_compose.components

import android.content.Context
import android.graphics.Insets.add
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.gify.theme_compose.R
import com.gify.theme_compose.composethemevalues.Theme

@Composable
fun ComposeGifItemComponent(
    modifier: Modifier = Modifier,
    model: GifItemModel,
    onClick: ()-> Unit
){
    ConstraintLayout(
        modifier
            .fillMaxWidth().defaultMinSize(minHeight = 250.dp)
            .clickable(onClick = onClick)

    ) {
        val(gifUrlRef, gifAnchorBarRef, gifTitleRef) = createRefs()
        val anchorMargin = Theme.dimens.dimen_unit_1
        val titleMargin = Theme.dimens.dimen_unit_2_5
        val defaultMinHeight = Theme.dimens.dimen_unit_25

        val barrier = createTopBarrier(gifAnchorBarRef, gifTitleRef, margin = 20.dp)
        
        if(model.gifUrl!=null){
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()

            SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(model.gifUrl)
                .crossfade(true)
                .build(), imageLoader = imageLoader,
                loading = {
                    Box(modifier = Modifier.size(12.dp), contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }

                },
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .height(
                        height = if (model.gifUrlMinHeight != null)
                            model.gifUrlMinHeight.dp else defaultMinHeight
                    )
                    .constrainAs(gifUrlRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(barrier, titleMargin)
                    },
                contentScale = ContentScale.Crop
            )
        }

        if (model.isAnchorBarVisibe){
            Image(
                painter = painterResource(id = R.drawable.small_anchor_bar),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .constrainAs(gifAnchorBarRef) {
                        linkTo(start = parent.start, end = parent.end, anchorMargin)
                        linkTo(
                            top = gifUrlRef.bottom,
                            bottom = gifTitleRef.top,
                            anchorMargin,
                            bottomGoneMargin = anchorMargin
                        )
                    }

            )
        }

        if (model.gifName!=null){
            Text(text = model.gifName,
            style = Theme.typography.body1,
            textAlign = TextAlign.Center,
                modifier = modifier
                    .constrainAs(gifTitleRef){
                        linkTo(start = parent.start, end = parent.end, titleMargin)
                        linkTo(top = gifAnchorBarRef.bottom, bottom = parent.bottom, titleMargin)
                    }
            )
        }

        

    }
}