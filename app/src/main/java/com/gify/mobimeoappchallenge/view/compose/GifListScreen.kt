package com.gify.mobimeoappchallenge.view.compose


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gify.mobimeoappchallenge.viewmodel.GifViewModel
import androidx.paging.compose.items
import com.gify.theme_compose.components.ComposeGifItemComponent
import com.gify.theme_compose.components.ErrorItem
import com.gify.theme_compose.components.GifItemModel
import com.gify.theme_compose.components.LoadingItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GifListScreen(viewmodel:GifViewModel){
    val gifs = viewmodel.composeGifs.collectAsLazyPagingItems()

    LazyVerticalGrid(cells = GridCells.Fixed(2)){

        items(gifs){ gif ->
            ComposeGifItemComponent(model =
            GifItemModel(gif?.url, gif?.title), onClick = {})

        }
        when (gifs.loadState.append) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item { LoadingItem() }
            }
            is LoadState.Error -> {
                item {
                    ErrorItem(message = (gifs.loadState.append as LoadState.Error).error.message.toString())
                }
            }
        }
    }
}

/*
@ExperimentalFoundationApi
public fun <T: Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        itemContent(lazyPagingItems[index])
    }
}*/
@ExperimentalFoundationApi
fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable LazyItemScope.(item: T?) -> Unit
) {
    items(
        count = items.itemCount,
        if (key == null) null else { index ->
            val item = items.peek(index)
            (if (item == null) {
                PagingPlaceholderKey(index)
            } else {
                key(item)
            }) as GridItemSpan
        }
    ) { index ->
        itemContent(items[index])
    }
}

@SuppressLint("BanParcelableUsage")
private data class PagingPlaceholderKey(private val index: Int) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<PagingPlaceholderKey> =
            object : Parcelable.Creator<PagingPlaceholderKey> {
                override fun createFromParcel(parcel: Parcel) =
                    PagingPlaceholderKey(parcel.readInt())

                override fun newArray(size: Int) = arrayOfNulls<PagingPlaceholderKey?>(size)
            }
    }
}
