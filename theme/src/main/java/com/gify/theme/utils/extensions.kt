package com.gify.theme.utils

import android.content.Context
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide



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

fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

/*
fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

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

}*/
