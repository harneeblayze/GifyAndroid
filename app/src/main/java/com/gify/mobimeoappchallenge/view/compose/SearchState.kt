package com.gify.mobimeoappchallenge.view.compose

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.gify.domain.model.GifItem
import kotlinx.coroutines.flow.*

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    searchResults: Flow<LazyPagingItems<GifItem>>
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
    //var suggestions by mutableStateOf(suggestions)
    var searchResults by mutableStateOf(searchResults)

    val searchDisplay: SearchDisplay
        get() = when {
            !focused && query.text.isEmpty()&& !searching -> SearchDisplay.InitialResults
            focused && query.text.isEmpty() && !searching-> SearchDisplay.Results//prev results
            focused && query.text.isNotEmpty() && !searching -> SearchDisplay.Results
            focused && query.text.isNotEmpty()&&searching -> SearchDisplay.Loading
            /*searchResults.count().equals(null)*/
            searchResults.map {
                it.itemCount == 0
            }.equals(true) -> SearchDisplay.NoResults
            else -> SearchDisplay.Loading
        }

    override fun toString(): String {
        return "🚀 State query: $query, focused: $focused, searching: $searching " +
                /*"searchResults: ${searchResults.itemSnapshotList}, " +*/
                " searchDisplay: $searchDisplay"

    }
}

@Composable
fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    //suggestions: List<SuggestionModel> = emptyList(),
    searchResults: Flow<LazyPagingItems<GifItem> > = emptyFlow()
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
            searchResults = searchResults
        )
    }
}