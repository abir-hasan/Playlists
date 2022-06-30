package com.abir.hasan.androidtdd.ui.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abir.hasan.androidtdd.data.Playlist
import com.abir.hasan.androidtdd.data.remote.PlaylistService
import com.abir.hasan.androidtdd.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }

    private var _binding: FragmentPlaylistBinding? = null

    private val binding: FragmentPlaylistBinding
        get() = _binding!!

    lateinit var viewModel: PlaylistViewModel

    lateinit var viewModelFactory: PlaylistViewModelFactory

    private val service = PlaylistService()
    private val repository = PlaylistRepository(service)


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
        viewModel.playlists.observe(viewLifecycleOwner) { lists ->
            lists.getOrNull()?.let {
                setupList(view, it)
            } ?: run {
                // TODO - Error Handling
            }
        }
    }

    private fun setupList(
        view: View,
        lists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MyPlaylistRecyclerViewAdapter(lists)
        }
    }

    private fun setupViewModel() {
        viewModelFactory = PlaylistViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistViewModel::class.java]
    }
}