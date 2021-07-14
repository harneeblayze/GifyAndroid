package com.gify.core.utils

import android.content.Context
import android.graphics.ColorFilter
import android.widget.ImageView
import android.widget.SearchView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 60f
        start()

    }

}

fun ImageView.loadGif(url:String?, progressDrawable: CircularProgressDrawable) {
    Glide.with(this)
        .asGif()
        .load(url)
        .placeholder(progressDrawable)
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