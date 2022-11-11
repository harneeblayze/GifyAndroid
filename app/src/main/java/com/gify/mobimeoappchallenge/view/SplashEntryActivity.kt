package com.gify.mobimeoappchallenge.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gify.mobimeoappchallenge.R
import com.gify.mobimeoappchallenge.databinding.ActivitySplashEntryBinding
import com.gify.mobimeoappchallenge.view.compose.GifComposeActivity

class SplashEntryActivity : AppCompatActivity() {
    private lateinit var splashEntryBinding: ActivitySplashEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashEntryBinding = ActivitySplashEntryBinding.inflate(layoutInflater)
        setContentView(splashEntryBinding.root)

        splashEntryBinding.legacy.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        splashEntryBinding.compose.setOnClickListener {
            startActivity(Intent(this, GifComposeActivity::class.java))
        }
    }
}