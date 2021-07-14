package com.gify.mobimeoappchallenge.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.gify.core.utils.SpacesItemDecoration
import com.gify.core.utils.getQueryTextChangeStateFlow
import com.gify.mobimeoappchallenge.R
import com.gify.mobimeoappchallenge.adapter.GifLoadStateAdapter
import com.gify.mobimeoappchallenge.adapter.GifyPagingAdapter
import com.gify.mobimeoappchallenge.databinding.FragmentGifyListBinding
import com.gify.mobimeoappchallenge.viewmodel.GifViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GifyListFragment : Fragment() {

    private val viewModel: GifViewModel by viewModels()
    private lateinit var gifAdapter: GifyPagingAdapter
    private lateinit var binding: FragmentGifyListBinding
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGifyListBinding.inflate(inflater, container, false)
        gifAdapter = GifyPagingAdapter {
            findNavController().navigate(GifyListFragmentDirections.actionGifyListFragmentToGifyDetailFragment(it?.images?.downsized_medium?.url, it?.title))
        }

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showUiState()
        bindUItoAdapter()
        listenToQueryInSearchView()




        lifecycleScope.launch {
            gifAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvGify.scrollToPosition(0) }
        }

    }


    private fun showUiState(){
        gifAdapter.addLoadStateListener { loadState ->
            // show empty list
            val isListEmpty = loadState.refresh is LoadState.NotLoading && gifAdapter.itemCount == 0
            showEmptyList(isListEmpty)

            binding.rvGify.isVisible = loadState.source.refresh is LoadState.NotLoading

            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireActivity(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyState.visibility = View.VISIBLE
            binding.rvGify.visibility = View.GONE
        } else {
            binding.emptyState.visibility = View.GONE
            binding.rvGify.visibility = View.VISIBLE
        }
    }

    private fun bindUItoAdapter(){
        binding.rvGify.apply {
            adapter = gifAdapter.withLoadStateHeaderAndFooter(
                header = GifLoadStateAdapter { gifAdapter.retry() },
                footer = GifLoadStateAdapter { gifAdapter.retry() }
            )
            layoutManager = GridLayoutManager(requireActivity(), 2)
            addItemDecoration(SpacesItemDecoration(30))
        }

    }

    private fun listenToQueryInSearchView(){
        binding.svGify.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    viewModel.searchGif(newText).collectLatest {
                        gifAdapter.submitData(it)
                    }
                }

                return true
            }
        })
    }




}