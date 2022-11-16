package com.gify.mobimeoappchallenge.view.compose


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gify.mobimeoappchallenge.viewmodel.GifViewModel
import com.gify.theme_compose.components.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GifListScreen(
    /*navController: NavController,*/
    viewModel: GifViewModel,
    state: SearchState = rememberSearchState()
) {

    val gifs = viewModel.composeGifs(state.query.text).collectAsLazyPagingItems()

    //
    val query = remember{
        state.query
    }

    Column(Modifier.fillMaxSize()) {

        SearchView(
            query = query,
            onQueryChange = {
                state.query = it
            },
            onSearchFocusChange = { state.focused = it },
            onClearQuery = { state.query = TextFieldValue("") },
            searching = state.searching,
            focused = state.focused,
        )
        LaunchedEffect(state.query.text) {
                state.searching = true
                //delay(100)
                state.searchResults = flowOf(gifs)
                state.searching = false
        }

        when (state.searchDisplay) {
            SearchDisplay.InitialResults -> {
                //state.searching = false
                EmptyGifState()
            }
            SearchDisplay.NoResults -> {
                //state.searching = false
                EmptyGifState()
            }

            SearchDisplay.Loading -> {
                state.searching = true
                LoadingItem()
            }

            SearchDisplay.Results -> {

                LazyVerticalGrid(cells = GridCells.Fixed(2)) {

                    items(gifs) { gif ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 16.dp,
                                    start = 16.dp, end = 16.dp, bottom = 8.dp
                                )
                        ) {
                            ComposeGifItemComponent(model =
                            GifItemModel(gif?.url, gif?.title, gifUrlMinHeight = 200),
                                onClick = {})
                        }


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
            else -> {
                LoadingItem()
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
