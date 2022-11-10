package com.gify.theme

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.gify.theme.databinding.GifItemComponentBinding
import com.gify.theme.utils.convertPixelsToDp
import com.gify.theme.utils.getProgressDrawable
import com.gify.theme.utils.loadGif

class GifItemComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) :ConstraintLayout(context, attrs){
    private val gifItemComponentBinding =
        GifItemComponentBinding.inflate(
            LayoutInflater.from(context), this)

    var gifTitle:String? = null
        set(value){
            field = value
            gifItemComponentBinding.tvGifName.text = value
        }

    var gifImage: Drawable? = null
        set(value) {
            field = value
            gifItemComponentBinding.sivGifImage.setImageDrawable(value)
        }

    var anchorBarVisible:Boolean = false
        set(value) {
            field = value
            gifItemComponentBinding.anchorBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    var anchorBarDrawable:Drawable? = null
        set(value) {
            field = value
            gifItemComponentBinding.anchorBar.setImageDrawable(value)
        }

    var gifImageMinHeight:Int? = null
        set(value) {
            field = value
            value?.let { gifItemComponentBinding.sivGifImage.minimumHeight = it }

        }




    init {
        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.GifItemComponent)
                .apply {
                    gifTitle = getString(R.styleable.GifItemComponent_gif_name)
                    gifImage = getDrawable(R.styleable.GifItemComponent_gif_url)
                    anchorBarVisible =
                        getBoolean(R.styleable.GifItemComponent_bar_anchor_isVisible, false)
                    anchorBarDrawable = getDrawable(R.styleable.GifItemComponent_bar_anchor_src)
                    gifImageMinHeight = getDimensionPixelSize(R.styleable.GifItemComponent_gif_url_min_height,0)



                }
        }?.recycle()

    }

    fun setGifUrl(uri:String?){
        if(uri.isNullOrBlank()) return setGifDrawable(null)
        gifItemComponentBinding.apply {
            sivGifImage.loadGif(uri, getProgressDrawable(context))
        }
    }

    fun setGifDrawable(drawable: Drawable?){
        gifItemComponentBinding.apply {
            sivGifImage.setImageDrawable(drawable)
            sivGifImage.isVisible = drawable!=null
        }

    }


    }