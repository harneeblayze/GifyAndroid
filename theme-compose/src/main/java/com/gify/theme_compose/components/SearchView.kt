package com.gify.theme_compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gify.theme_compose.R

@Composable
fun SearchView(/*state: MutableState<TextFieldValue>,*/
               query: TextFieldValue,
               onQueryChange: (TextFieldValue) -> Unit,
               onSearchFocusChange: (Boolean) -> Unit,
               onClearQuery: () -> Unit,
               searching: Boolean,
               focused: Boolean,
) {
    val focusRequester = remember { FocusRequester() }
    TextField(
        value = query,//state.value,
        onValueChange = onQueryChange/*{ value ->
            state.value = value
        }*/,
        modifier = Modifier
            .fillMaxWidth().padding(start = 16.dp, end = 16.dp)
            .onFocusChanged {
                onSearchFocusChange(it.isFocused)
            }.focusRequester(focusRequester),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                tint = Color.DarkGray,
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon ={
            when{
                searching -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(36.dp)
                    )
                }
                query.text.isNotEmpty() -> {
                    IconButton(onClick = onClearQuery) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(16.dp)
                        )
                        /*Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )*/
                        //Icon(imageVector = Icons.Filled.Cancel, contentDescription = null)
                    }
                }
            }
        },
        /*trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },*/
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            cursorColor =  Color.DarkGray,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = colorResource(id = R.color.white),
            focusedIndicatorColor = Color.Gray,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
    /*when{
        searching -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .size(36.dp)
            )
        }
        query.text.isNotEmpty() -> {
            IconButton(onClick = onClearQuery) {
                Icon(imageVector = Icons.Filled.Cancel, contentDescription = null)
            }
        }
    }*/
}


