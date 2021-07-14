package com.gify.mobimeoappchallenge.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.gify.core.utils.loadGif
import com.gify.mobimeoappchallenge.R
import com.gify.mobimeoappchallenge.databinding.FragmentGifyDetailBinding
import com.gify.mobimeoappchallenge.databinding.FragmentGifyListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifyDetailFragment : Fragment() {
    private lateinit var binding: FragmentGifyDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGifyDetailBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title =  arguments?.getString("title")
        val url = arguments?.getString("url")

        binding.apply {
            imgGifyDetail.loadGif(url)
        }

        //sivGifImage.loadGif(gifModel?.images?.downsized_medium?.url)
    }
}