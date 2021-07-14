package com.gify.core.utils

import android.widget.ImageView
import android.widget.SearchView
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun ImageView.loadGif(url:String?) {
    Glide.with(this)
        .asGif()
        .load(url)
        .into(this)
}

fun android.widget.SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query

}