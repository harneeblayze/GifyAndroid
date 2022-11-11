package com.gify.theme_compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
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
            .fillMaxWidth().clickable(onClick = onClick)

    ) {
        val(gifUrlRef, gifAnchorBarRef, gifTitleRef) = createRefs()
        val anchorMargin = Theme.dimens.dimen_unit_1
        val titleMargin = Theme.dimens.dimen_unit_2
        val defaultMinHeight = Theme.dimens.dimen_unit_25
        
        if(model.gifUrl!=null){
            Image(painter = rememberAsyncImagePainter(model.gifUrl),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .defaultMinSize(
                        minHeight = if (model.gifUrlMinHeight != null)
                            model.gifUrlMinHeight.dp else defaultMinHeight
                    )
                    .constrainAs(gifUrlRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(gifAnchorBarRef.top)
                    }
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
                        linkTo(top = gifUrlRef.bottom, bottom = gifTitleRef.top, anchorMargin)
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