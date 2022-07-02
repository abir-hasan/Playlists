package com.abir.hasan.androidtdd.ui.playlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.databinding.FragmentPlaylistBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }

    private var _binding: FragmentPlaylistBinding? = null

    private val binding: FragmentPlaylistBinding
        get() = _binding!!

    private lateinit var viewModel: PlaylistViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupObserver(view)
    }

    private fun setupObserver(view: View) {
        Log.d("TEST", "setupObserver() called")
        viewModel.playLists.observe(viewLifecycleOwner) { lists ->
            lists.getOrNull()?.let {
                setupList(it)
            } ?: run {
                Log.e("TEST", "setupObserver() error")
            }
        }

        viewModel.loader.observe(viewLifecycleOwner) { loading ->
            binding.loader.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }

    private fun setupList(
        lists: List<Playlist>
    ) {
        with(binding.rvPlaylist) {
            Log.d("TEST", "setupList() called")
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MyPlaylistRecyclerViewAdapter(lists, ::onPlaylistsItemClick)
        }
    }

    private fun onPlaylistsItemClick(playlistId: String) {
        val action: NavDirections =
            PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailFragment(playlistId)
        findNavController().navigate(action)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistViewModel::class.java]
    }
}